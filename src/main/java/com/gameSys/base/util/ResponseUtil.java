package com.gameSys.base.util;

import com.gameSys.monitor.response.RespResult;

public class ResponseUtil {
	public static final String SUCCESS="0000";
    public static final String UNKNOWN="9999";
    public static final String LACK_PARAM="0001";
    public static final String PARAM_NOT_ALLOW="0002";
    public static final String VERIFY_ALREADY_NOT_VALIDATE="0003";
    public static final String EXCEED_MAX_COUNT="0004";
    public static final String VERIFY_NOT_MATCH="0005";
    public static final String VERIFY_NOT_EXIST="0006";
    public static final String RIGHT_NOT_ALLOW="0007";
    public static final String SIGN_ERROR="0009";
    public static final String OPERATE_NOT_ALLOW="0008";
    public static final String USER_ALREADY_EXIST="0100";
    public static final String USER_ALREADY_LOCK="0102";
    public static final String LOGINNAME_PASSWORD_ERROR="0104";
    public static final String USER_NOT_EXIST="0106";
    public static final String USER_WAITING_AUDIT="0107";
    public static final String SERIALNUMBER_NOT_ALLOW="0108";
    public static final String ACCESS_TOKEN_OUT_OF_DATE="0109";
    public static final String ACCOUNT_NOT_ENOUGH="0200";
    public static final String NOT_FOUND = "0404";
    
    public static boolean isSuccessResponse(RespResult result){
    	if(result == null){
    		return false;
    	}
    	if(SUCCESS.equals(result.getResultCode())){
    		return true;
    	}
    	return false;
    }
    
    public static RespResult createResponseResult(String code, String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(code);
    	result.setResultMsg(msg);
    	return result;
    }
    
    public static RespResult createResponseResult(){
        RespResult result=new RespResult();
        return result;
    }
    
    /**
     * 创建成功响应
     * @return
     */
    public static RespResult createSuccessResponseResult(){
    	RespResult result = new RespResult();
    	result.setResultCode(SUCCESS);
    	return result;
    }
    
    /**
     * 创建成功响应
     * @return
     */
    public static RespResult createSuccessResponseResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(SUCCESS);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建缺少参数响应
     * @return
     */
    public static RespResult createLackParamResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(LACK_PARAM);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建参数不允许响应
     * @return
     */
    public static RespResult createParamNotAllowResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(PARAM_NOT_ALLOW);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建用户已存在响应
     * @return
     */
    public static RespResult createUserAlreadyExistResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(USER_ALREADY_EXIST);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建验证码已经失效响应
     * @return
     */
    public static RespResult createVerifyAlreadyNotValidateResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(VERIFY_ALREADY_NOT_VALIDATE);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建超过最大次数响应
     * @return
     */
    public static RespResult createExceedMaxCountResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(EXCEED_MAX_COUNT);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建验证码不匹配响应
     * @return
     */
    public static RespResult createVerifyNotMatchResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(VERIFY_NOT_MATCH);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建验证码不存在响应
     * @return
     */
    public static RespResult createVerifyNotExistResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(VERIFY_NOT_EXIST);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建未知错误响应
     * @return
     */
    public static RespResult createUnknownResult(){
        RespResult result=new RespResult();
        result.setResultCode(UNKNOWN);
        return result;
    }
    
    /**
     * 创建错误信息的未知错误响应
     * @param msg
     * @return
     */
    public static RespResult createUnknownResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(UNKNOWN);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建签名错误响应
     * @return
     */
    public static RespResult createSignErrorResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(SIGN_ERROR);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建签名错误响应
     * @return
     */
    public static RespResult createSignErrorResult(){
        RespResult result=new RespResult();
        result.setResultCode(SIGN_ERROR);
        result.setResultMsg("签名错误");
        return result;
    }
    
    /**
     * 创建用户不存在错误响应
     * @return
     */
    public static RespResult createUSERNOTEXISTResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(USER_NOT_EXIST);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建用户不存在错误响应
     * @return
     */
    public static RespResult createAccountNoMoneyResult(String msg){
        RespResult result=new RespResult();
        result.setResultCode(ACCOUNT_NOT_ENOUGH);
        result.setResultMsg(msg);
        return result;
    }
    
    /**
     * 创建权限不足的错误响应
     * @param msg
     * @return
     */
    public static RespResult createRightNotAllowResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(RIGHT_NOT_ALLOW);
    	result.setResultMsg(msg);
    	return result;
    }
    
    /**
     * 创建资源不存在的错误响应
     * @param msg
     * @return
     */
    public static RespResult createNotFoundResult(String msg){
    	RespResult result = new RespResult();
    	result.setResultCode(NOT_FOUND);
    	result.setResultMsg(msg);
    	return result;
    }
    
}
