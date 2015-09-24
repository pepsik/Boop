package org.pepsik.rest.mvc;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.pepsik.core.models.entities.Password;
import org.pepsik.core.models.entities.Profile;
import org.pepsik.core.models.entities.User;
import org.pepsik.core.services.SmartService;
import org.pepsik.rest.exceptions.*;
import org.pepsik.rest.resources.AccountListResource;
import org.pepsik.rest.resources.AccountResource;
import org.pepsik.rest.resources.asm.AccountListResourceAsm;
import org.pepsik.rest.resources.asm.AccountResourceAsm;
import org.pepsik.rest.utilities.AccountList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping
public class UserController {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SmartService service;

    //    @Value("${upload.path}")
    private String uploadPath;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern(DATE_PATTERN);

        binder.registerCustomEditor(DateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                try {
                    setValue(fmt.parseDateTime(text));
                } catch (IllegalArgumentException ex) {
                    binder.getBindingResult().rejectValue("birthdate", "birthdate.emptyOrInvalid");
                }
            }

            @Override
            public String getAsText() {
                return fmt.print((DateTime) getValue());
            }
        });
    }

    @RequestMapping(value = "/rest/accounts", method = RequestMethod.GET)
    public ResponseEntity<AccountListResource> getAccounts(@RequestParam(required = false) String username) {
        AccountList list;
        if (username == null) {
            list = new AccountList(service.getAllUsers());
        } else {
            User account = null;
            try {
                account = service.getUser(username);
            } catch (Exception e) {/*empty*/}

            list = new AccountList(new ArrayList<>());
            if (account != null)
                list.setAccounts(Collections.singletonList(account));
        }

        AccountListResource resources = new AccountListResourceAsm().toResource(list);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/rest/accounts", method = RequestMethod.POST)
    public ResponseEntity<AccountResource> createAccount(@RequestBody AccountResource sentAccount) {
        logger.debug("Attempt to create account with login " + sentAccount.getUsername() + " and password " + sentAccount.getPassword());
        try {
            service.isExistUsername(sentAccount.getUsername());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); //TODO: refact
        } catch (Exception e) {/*empty*/ }

        User user = new User();
        user.setUsername(sentAccount.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setUserPassword(new Password(encoder.encode(sentAccount.getPassword())));
        user.getUserPassword().setUser(user);
        service.saveUser(user);
        logger.debug("User successful created!");
        return new ResponseEntity<>(sentAccount, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/rest/accounts/{accountId}", method = RequestMethod.GET)
    public ResponseEntity<AccountResource> getAccount(@PathVariable Long accountId) {
        User account = service.getUser(accountId);
        if (account != null) {
            AccountResource res = new AccountResourceAsm().toResource(account);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{username}/profile", method = RequestMethod.GET)
    public String getUserProfile(@PathVariable("username") String username, Model model) {
        model.addAttribute(service.getProfile(username));
        model.addAttribute("username", username);
        model.addAttribute("commentsCount", service.getUserCommentsCount(username));
        model.addAttribute("postsCount", service.getUserPostsCount(username));
        model.addAttribute("favoritesCount", service.getUserFavoritesCount(username));
        return "user/view_profile";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") long id) {
        service.deleteUser(id);
        return "redirect:/home";
    }

    @RequestMapping(value = "/{username}/favorites/{pageId}", method = RequestMethod.GET, produces = "text/html")
    public String getUserFavorites(@PathVariable String username, @PathVariable(value = "pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long favoritesCount = service.getUserFavoritesCount(username);
        if (pageId != 1 && favoritesCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (favoritesCount > 0) {
            if (pageId > service.getPagesCount(favoritesCount))
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserFavorites(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, favoritesCount));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));

        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("favoritesCount", favoritesCount);
        model.addAttribute("postsCount", service.getUserPostsCount(username));
        model.addAttribute("commentsCount", service.getUserCommentsCount(username));
        return "user/favorites";
    }

    @RequestMapping(value = {"/{username}/posts/{pageId}"}, method = RequestMethod.GET, produces = "text/html")
    public String getUserPosts(@PathVariable("username") String username, @PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long postsCount = service.getUserPostsCount(username);
        if (pageId != 1 && postsCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (postsCount > 0) {
            if (pageId > service.getPagesCount(postsCount))
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserPosts(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, postsCount));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));


        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("postsCount", postsCount);
        model.addAttribute("favoritesCount", service.getUserFavoritesCount(username));
        model.addAttribute("commentsCount", service.getUserCommentsCount(username));
        return "user/posts";
    }

    @RequestMapping(value = {"/{username}/comments/{pageId}"}, method = RequestMethod.GET, produces = "text/html")
    public String getUserComments(@PathVariable("username") String username, @PathVariable("pageId") String strPageId, Model model) {
        int pageId;
        if (strPageId.length() != 0)
            try {
                pageId = Integer.parseInt(strPageId);
            } catch (NumberFormatException ex) {
                throw new BadRequestException();
            }
        else
            pageId = 1;

        long commentsCount = service.getUserCommentsCount(username);
        if (pageId != 1 && commentsCount == 0 || pageId <= 0)
            throw new ResourceNotFoundException();

        if (commentsCount > 0) {
            if (pageId > service.getPagesCount(commentsCount / 2))                  //TODO: comments defalut counter - (commentsCount / 2)
                throw new ResourceNotFoundException();

            model.addAttribute(service.getUserComments(username, pageId));
            model.addAttribute("pagination", service.getPagination(pageId, commentsCount / 2));
        } else
            model.addAttribute("pagination", Arrays.asList("1"));

        model.addAttribute("currentPageIndex", pageId);
        model.addAttribute("username", username);
        model.addAttribute("commentsCount", commentsCount);
        model.addAttribute("postsCount", service.getUserPostsCount(username));
        model.addAttribute("favoritesCount", service.getUserFavoritesCount(username));
        return "user/comments";
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public String uploadUserAvatar(@RequestParam("image") MultipartFile file) {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!loggedUser.equals("guest")) {
            if (!file.isEmpty())
                try {
                    byte[] bytes = file.getBytes();

                    //TODO: to prop file
                    File dir = new File(uploadPath + "\\avatars\\");
                    if (!dir.exists())
                        dir.mkdirs();

                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + loggedUser + ".jpeg");
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());
                } catch (Exception e) {
                    throw new ImageUploadException();
                }
        } else
            throw new NoAuthorizationException();
        return "redirect:/settings/profile";
    }

    @RequestMapping(value = "/upload/image", method = RequestMethod.POST)
    public HttpEntity<String> uploadUserImage(@RequestParam("image") MultipartFile file) {
        String path = "";
        if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("guest")) {
            if (!file.isEmpty())
                try {
                    byte[] bytes = file.getBytes();
                    File dir = new File(uploadPath + "\\images\\");
                    if (!dir.exists())
                        dir.mkdirs();

                    File serverFile = new File(dir.getAbsolutePath()
                            + File.separator + file.getOriginalFilename());
                    BufferedOutputStream stream = new BufferedOutputStream(
                            new FileOutputStream(serverFile));
                    stream.write(bytes);
                    stream.close();

                    logger.info("Server File Location="
                            + serverFile.getAbsolutePath());

                    path = "/uploads/images/" + URLEncoder.encode(serverFile.getName(), "UTF-8");
                    logger.info(serverFile.getCanonicalPath());
                } catch (Exception e) {
                    throw new ImageUploadException();
                }
        } else
            throw new NoAuthorizationException();

        return new HttpEntity<>(path);
    }
}
