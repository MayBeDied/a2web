package com.monitor.framework.base.service.impl;

import com.monitor.framework.base.dao.BaseDao;
import com.monitor.framework.base.entity.User;
import com.monitor.framework.base.entity.UserAvatar;
import com.monitor.framework.base.service.UserService;
import com.monitor.framework.utils.StrUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    @Resource
    private BaseDao baseDao;

    private List<User> getUsers() {

        return baseDao.find("from User");
    }

    public User getUserByLoginName(String loginName){
        return this.get("from User where loginName='"+loginName+"'");
    }

    @Override
    public UserAvatar getAvatarByUserId(String userId) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",userId);
        return this.get("from UserAvatar where userId=:userId",params);
    }

    @Override
    public void updateUserAvatar(User user,String dirPath) {
       if(StrUtil.isEmpty(user.getAvatarId()))
           return;
        UserAvatar userAvatar=this.get(UserAvatar.class,user.getAvatarId());
        userAvatar.setUserId(user.getId());
        String src=userAvatar.getSrc();
        File file = new File(dirPath +src);
        String newPath=src.replaceAll("new",user.getName());
        if (file.exists()) {
            file.renameTo(new File(dirPath+newPath));
        }
        userAvatar.setSrc(newPath);
        userAvatar.setName(userAvatar.getName().replaceAll("new",user.getName()));
        this.update(userAvatar);
    }


}
