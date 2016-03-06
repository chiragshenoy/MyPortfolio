package chiragshenoy.myportfolio.Parsers;

import chiragshenoy.myportfolio.Models.BaseModel;

/**
 * Created by Chirag Shenoy on 03-Mar-16.
 */
public abstract class BaseApiParser {

    protected String mResponse;


    public BaseApiParser(String response) {
        mResponse = response;
    }

    public abstract <T extends BaseModel> BaseModel getParserResponse();
}