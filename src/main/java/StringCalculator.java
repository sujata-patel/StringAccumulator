import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class StringCalculator {

    private String delimiters = ",|\n";

    int add(String s) throws Exception {

        if(s != null) {
            if(StringUtils.equals(s, "")){
                return 0;
            }else{
                if(isDelimiterPassed(s)) {
                    int newLineIndex = s.indexOf("\n");
                    String delimiterStr = s.substring(2, newLineIndex);

                    delimiters = Arrays.stream(delimiterStr.split("\\|")).map(delimiter -> Pattern.quote(delimiter)).collect(Collectors.joining("|"));
                    s= s.substring(newLineIndex+1);
                }
                Optional<String> negativeNumbers = Arrays.stream(s.split(delimiters)).filter(num -> Integer.valueOf(num) < 0)
                        .reduce(String::concat);
                if(negativeNumbers.isPresent()){
                    throw new Exception("negatives not allowed - "+negativeNumbers.get());
                }

                return Arrays.stream(s.split(delimiters)).map(Integer::valueOf).filter(num -> num <1001).reduce(Integer::sum).get();
            }
        }else{
            throw new Exception("Input is null");
        }
    }

    boolean isDelimiterPassed(String s) {
        return s.startsWith("//");
    }
}
