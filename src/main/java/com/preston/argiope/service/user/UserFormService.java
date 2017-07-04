package com.preston.argiope.service.user;

import com.preston.argiope.exception.common.form.MissingRequiredFieldException;
import com.preston.argiope.model.user.CreateUserForm;
import com.preston.argiope.model.user.User;

public interface UserFormService {

	User convertFormToUser(CreateUserForm form) throws MissingRequiredFieldException;

}
