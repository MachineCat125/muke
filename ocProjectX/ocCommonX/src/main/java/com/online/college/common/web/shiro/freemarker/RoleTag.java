package com.online.college.common.web.shiro.freemarker;

import java.io.IOException;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

/**
 *
* @Description: TODO
* @author cmazxiaoma
* @date 2018-02-07 18:57
* @version V1.0
 */
public abstract class RoleTag extends SecureTag {

    public String getName(Map params) {
        return getParam(params, "name");
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody(getName(params));

        if (show) {
            renderBody(env, body);
        }
    }

    protected abstract boolean showTagBody(String roleName);
}