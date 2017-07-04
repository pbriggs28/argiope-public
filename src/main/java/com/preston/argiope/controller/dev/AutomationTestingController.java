package com.preston.argiope.controller.dev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.preston.argiope.app.constant.dev.DevWebConstants;
import com.preston.argiope.app.constant.dev.DevWebConstants.RequestMappings.Dev;
import com.preston.argiope.app.constant.legacy.ArgiopeConstantTestElement;
import com.preston.argiope.controller.AbstractController;
import com.preston.argiope.model.dev.ResetIpBlockingForm;
import com.preston.argiope.service.security.LoginAttemptService;

@Controller()
public class AutomationTestingController extends AbstractController{
	
	@Autowired
	private LoginAttemptService loginAttemptService;
	
	/**
	 * See {@link #showPageResetIpBlockingPOST(ResetIpBlockingForm)} for details.
	 * @return
	 */
	@RequestMapping(value=Dev.AutomationTesting.Pages.RESET_IP_BLOCKING, method=RequestMethod.GET)
	public ModelAndView showPageResetIpBlockingGET() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName(AbstractController.Views.VIEW_RESET_IP_BLOCKING);
		mv.addObject(Spring.COMMAND, new ResetIpBlockingForm());
		
		return mv;
	}
	/**
	 * <p>Automation testing uses this URL to reset a block on the current IP address. This
	 * poses a security vulnerability so all URLs, variables etc. are disguised.</p>
	 * 
	 * <p>In the future, instead of Constant Strings, these will be Strings randomly generated at server start up
	 * or even better, generated as part of the {@link LoginAttemptService#startAutomationTestingMode()}
	 * process.</p>
	 * 
	 * <p>In order to remove the current machine's IP from the list of blocked IP addresses, 
	 * automation testing scripts should execute the following steps:</p>
	 * 
	 * <ul>
	 * 	<li>Enable Automation Testing Mode:
	 * 	{@link LoginAttemptService#startAutomationTestingMode()}.</li>
	 * 	<li>Navigate to {@link #REQ_MAP_RESET_IP_BLOCKING}.</li>
	 * 	<li>Enter {@link ResetIpBlockingForm#VALID_FORM_VALUE} in the <i>hidden</i> element
	 * 	with the class {@link ArgiopeConstantTestElement#CLASS_INPUT_RESET_IP_BLOCKING}.</li>
	 * 	<li>Submit the <i>hidden</i> form with the class {@link ArgiopeConstantTestElement#CLASS_FORM_RESET_IP_BLOCKING}.</li>
	 * </ul>
	 * 
	 * <p>In order for a malicious user to successfully unblock their IP address using this method they
	 * would need to accomplish the following (This is assuming the modifications have been made to 
	 * randomly generate the various String values at server start up).</p>
	 * 
	 * <ul>
	 * 	<li>Know the correct URL generated from the last server restart.</li>
	 * 	<li>Know the correct field input value generated from the last server restart.</li>
	 * </ul>
	 * @param formInput
	 * @return
	 */
	@RequestMapping(value=Dev.AutomationTesting.Pages.RESET_IP_BLOCKING, method=RequestMethod.POST)
	public ModelAndView showPageResetIpBlockingPOST(@ModelAttribute(Spring.COMMAND) ResetIpBlockingForm formInput) {
		final String ipBlockReset = DevWebConstants.ResponseAttributes.IpBlockReset.ATTRIBUTE;
		final String validValue = ResetIpBlockingForm.VALID_FORM_VALUE;
		ModelAndView mv = new ModelAndView();
		
		mv.addObject(ipBlockReset, "false");
		String inputVal = formInput.getFormInputValue();
		if(validValue != null && validValue.equals(inputVal)) {
			loginAttemptService.loginSucceeded();
			mv.addObject(ipBlockReset, "true");
		} else {
			mv.addObject(ipBlockReset, "false");
		}
		mv.setViewName(AbstractController.Views.VIEW_RESET_IP_BLOCKING);
		
		return mv;
	}
	
}
