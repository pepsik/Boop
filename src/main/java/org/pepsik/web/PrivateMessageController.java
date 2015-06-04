package org.pepsik.web;

import org.pepsik.model.PrivateMessage;
import org.pepsik.model.User;
import org.pepsik.service.SmartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.List;

/**
 * Created by pepsik on 5/26/15.
 */

@Controller
@RequestMapping(value = "/messages")
public class PrivateMessageController {

    @Autowired
    private SmartService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, "recipient", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.equals("")) {
                    binder.getBindingResult().rejectValue("recipient", "recipient.empty");
                    return;
                }
                User user = new User();
                user.setUsername(text);
                setValue(user);
            }

            @Override
            public String getAsText() {
                return "";
            }
        });

        binder.registerCustomEditor(User.class, "sender", new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (text == null || text.equals("")) {
                    binder.getBindingResult().rejectValue("sender", "recipient.empty");
                    return;
                }
                User user = new User();
                user.setUsername(text);
                setValue(user);
            }

            @Override
            public String getAsText() {
                return "";
            }
        });
    }

    @RequestMapping(method = RequestMethod.GET, produces = "text/html")
    public String getMainPage(Model model) {
        model.addAttribute(new PrivateMessage());
        model.addAttribute("outputPMCount", service.getOutputPMCount());
        model.addAttribute("inputPMCount", service.getInputPMCount());
        return "private_message/new";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String sendPrivateMessage(@Valid PrivateMessage privateMessage, BindingResult result) {
        if (!service.isExistUsername(privateMessage.getRecipient().getUsername()))
            result.rejectValue("recipient", "recipient.notExist");

        if (result.hasErrors())
            return "private_message/new";

        service.sendPrivateMessage(privateMessage);
        return "redirect:/messages/output";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET, produces = "text/html")
    public String getInputMessages(Model model) {
        List<PrivateMessage> inputPrivateMessages = service.getInputPrivateMessages();
        model.addAttribute("inputPMList", inputPrivateMessages);
        model.addAttribute("inputPMCount", inputPrivateMessages.size());
        model.addAttribute("outputPMCount", service.getOutputPMCount());
        return "private_message/input";
    }

    @RequestMapping(value = "output", method = RequestMethod.GET, produces = "text/html")
    public String getOutputMessages(Model model) {
        List<PrivateMessage> outputPrivateMessages = service.getOutputPrivateMessages();
        model.addAttribute("outputPMList", outputPrivateMessages);
        model.addAttribute("outputPMCount", outputPrivateMessages.size());
        model.addAttribute("inputPMCount", service.getInputPMCount());
        return "private_message/output";
    }
}
