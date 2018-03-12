package com.monitor.framework.oauth.common;

import com.monitor.framework.oauth.entity.OAuthUser;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * Created by billJiang on 2017/1/15.
 * e-mail:jrn1012@petrochina.com.cn qq:475572229
 */
public interface CustomOAuthService extends OAuthService {
    String getoAuthType();
    String getAuthorizationUrl();
    OAuthUser getOAuthUser(Token accessToken);
    String getBtnClass();
}
