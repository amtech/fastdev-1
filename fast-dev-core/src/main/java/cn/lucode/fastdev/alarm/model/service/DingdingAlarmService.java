package cn.lucode.fastdev.alarm.model.service;

import cn.lucode.fastdev.alarm.model.MessageModel;

/**
 * @author yunfeng.lu
 * @create 2018/3/18.
 */
public interface DingdingAlarmService {

    void textMessage(MessageModel messageModel);


}
