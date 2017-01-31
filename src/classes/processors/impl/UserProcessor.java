package classes.processors.impl;

import classes.response.ResponseDTO;
import classes.response.impl.UserResponse;
import classes.model.User;
import classes.processors.ResponseProcessor;
import java.util.Collections;
import java.util.List;

public class UserProcessor implements ResponseProcessor {

    @Override
    public List<User> process(ResponseDTO response) {
        UserResponse userResponse = (UserResponse) response;
        User user = new User(userResponse.getUserId(), userResponse.getName(), userResponse.getSurname(), userResponse.getEmail(), userResponse.getPhone(), userResponse.getAddress(),
                userResponse.getLogin(), userResponse.getPassword(), userResponse.getVersion(), userResponse.getPrivilege());
        return Collections.singletonList(user);
    }

}
