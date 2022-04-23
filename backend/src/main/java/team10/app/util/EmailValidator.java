package team10.app.util;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        String regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; // Email regex of RFC5322 Standard: https://www.rfc-editor.org/info/rfc5322
        return regexPattern.matches(s);
    }
}
