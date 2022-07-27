package com.javatpoint.service;

import com.jcraft.jsch.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SFTPService {

    @Value("${edmpscheduler.remoteHost:}")
    String remoteHost;

    @Value("${edmpscheduler.userName:}")
    String userName;

    @Value("${edmpscheduler.remotePath:}")
    String remotePath;

    @Value("${edmpscheduler.privateKey:}")
    String privateKey;

    @Value("${edmpscheduler.knownHost:}")
    String knownHost;

    @Value("${edmpscheduler.passPhrase:}")
    String passPhrase;


    public String send(){
        Session jschSession = null;
        String edmpStatus = "";
        try{
            JSch jSch = new JSch();
            log.info("knownHost:"+knownHost);
            log.info("privateKey:"+privateKey);
            log.info("passPhrase:"+passPhrase);
            log.info("userName:"+userName);
            log.info("remoteHost:"+remoteHost);
            jSch.setKnownHosts(knownHost);
            jSch.addIdentity(privateKey,passPhrase);
            jschSession = jSch.getSession(userName,remoteHost);
            Channel sftp = jschSession.openChannel("sftp");
            sftp.connect();
            ChannelSftp channelSftp = (ChannelSftp) sftp;
            channelSftp.put("C://user//abc.txt",remotePath);
            channelSftp.exit();
        }catch(JSchException | SftpException e){
            log.info("catch block with error");
        }finally {
            if(jschSession != null){
                jschSession.disconnect();
            }
        }
        return "success edmp connection";
    }
}
