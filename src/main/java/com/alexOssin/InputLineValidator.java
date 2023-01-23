package com.alexOssin;

import java.util.function.Function;
import static com.alexOssin.ValidationResult.*;

/*      To consider :
        1. special character validation
        2. duplicate lines
        3. lower and upper cases
        Please note : Since we're assuming that the file represents a valid network structure ,
        no validation for groups vs its representation as lines in the input file implemented .
     */

public interface InputLineValidator extends Function<InputLine,ValidationResult> {

    Integer MAX_NODE_NAME_LENGTH_CHAR =20;

    /*
    NOTE : since we're assuming that an input file is valid by default ,
    isNodeNameLengthValid() validates only the first field of the line ,
    assuming that a node with name that exceeds "max node name length" will appear as a first field in one of the other
    lines .
     */
    static InputLineValidator isNodeNameLengthValid(){
        return line->line.getFirstField().length() <= MAX_NODE_NAME_LENGTH_CHAR ? VALID_LINE : NODE_NAME_EXCEEDS_MAX_LENGTH;
    }


    static InputLineValidator isNonEmptyFirstField(){
        return line->line.getFirstField().length() == 0 ? EMPTY_FIRST_FIELD_IS_NOT_ALLOWED : VALID_LINE;
    }

    static InputLineValidator isNonEmptyLine(){
        return line->(line.getFirstField().length() == 0 && line.getConnectedUnits().isEmpty())
                ? EMPTY_LINES_ARE_NOT_ALLOWED : VALID_LINE;

    }

    default InputLineValidator and (InputLineValidator other){
        return line->{
            ValidationResult validationRes = this.apply(line);
            return validationRes.equals(ValidationResult.VALID_LINE) ? other.apply(line) : validationRes;
        };
    }

}
