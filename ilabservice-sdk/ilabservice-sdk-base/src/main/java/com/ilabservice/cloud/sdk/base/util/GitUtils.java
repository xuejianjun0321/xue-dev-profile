package com.ilabservice.cloud.sdk.base.util;

import com.ilabservice.cloud.sdk.base.exception.BaseRuntimeException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.util.List;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月24日 14:46:42
 * @Description: Git操作工具类
 */
@Log4j2
public class GitUtils {

    /**
     * 设置下载存放路径
     */
    private static String LOCAL_REPO_PATH;

    /**
     * 上传文件地址
     */
    private static String LOCAL_REPOGIT_CONFIG;
    /**
     * git仓库地址(ssh)
     */
    private static String SSH_REMOTE_REPO_URI;
    /**
     * 用户名
     */
    private static String GIT_USERNAME;
    /**
     * 密码
     */
    private static String GIT_PASSWORD;

    /**
     * 分支名称
     */
    private static String BRANCH_NAME;


    static {
        SSH_REMOTE_REPO_URI = "http://gitlab.devops.intelab.cloud/jj.xue/lowcode-test.git";
        GIT_USERNAME = "jj.xue";
        GIT_PASSWORD = "OqDnRimzHr";
    }


    public static void initBranchName(String branchName) {
        BRANCH_NAME = branchName;
    }

    /**
     * 提交并推送代码至远程服务器
     *
     * @param projectPath 提交文件路径(相对路径)
     * @param desc        提交描述
     * @return
     */
    public static void commitAndPush(String projectPath, String desc) {
        try (Git git = Git.open(new File(projectPath))) {
            UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(GIT_USERNAME, GIT_PASSWORD);
            //添加
            git.add().addFilepattern(".").call();
            //提交
            git.commit().setAll(true).setMessage(desc).call();
            //推送到远程
            if (org.apache.commons.lang3.StringUtils.isBlank(GIT_USERNAME) || StringUtils.isBlank(GIT_PASSWORD)) {
                git.push().setForce(true).setForce(true).call();
            } else {
                git.push().setCredentialsProvider(provider).setForce(true).call();
            }
            log.info("Git Commit And Push file " + projectPath + " to repository at " + git.getRepository().getDirectory());
        } catch (Exception e) {
            log.error("Git Commit And Push error!------>" + e);
            throw new BaseRuntimeException("Git Commit And Push error" + e.getMessage());
        }

    }

    /**
     * 拉取远程代码
     */
    public static void pull() {
        try (Git git = Git.open(new File(LOCAL_REPOGIT_CONFIG))) {
            UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(GIT_USERNAME, GIT_PASSWORD);
            git.pull().setCredentialsProvider(provider).call();
        } catch (Exception e) {
            log.error("Git pull error!------>" + e.getMessage());
        }
    }

    /**
     * 拉取远端分支到本地
     */
    public static void checkout() {
        try (Git git = Git.open(new File(LOCAL_REPOGIT_CONFIG))) {
            if (branchNameExist(git)) {
                git.checkout().setName(BRANCH_NAME).setCreateBranch(false).call();
            } else {
                git.checkout().setName(BRANCH_NAME).setCreateBranch(true).setStartPoint("origin/" + BRANCH_NAME).call();
            }
            UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(GIT_USERNAME, GIT_PASSWORD);
            //拉取最新的提交
            git.pull().setCredentialsProvider(provider).call();
        } catch (Exception e) {
            log.error("Git checkout error!------>" + e.getMessage());
        }
    }


    /**
     * <p>
     * Description:判断本地分支名是否存在
     * </p>
     *
     * @param git git
     * @return boolean
     * @throws GitAPIException GitAPIException
     * @author wgs
     * @date 2019年7月20日 下午2:49:46
     */
    public static boolean branchNameExist(Git git) throws GitAPIException {
        List<Ref> refs = git.branchList().call();
        for (Ref ref : refs) {
            if (ref.getName().contains(BRANCH_NAME)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 创建仓库，仅需要执行一次
     */
    public static void setupRepository() {
        try {
            //设置远程服务器上的用户名和密码
            UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(GIT_USERNAME, GIT_PASSWORD);
            //设置远程URI
            Git.cloneRepository().setURI(SSH_REMOTE_REPO_URI)
                    //设置clone下来的分支,默认master
                    .setBranch("master")
                    //设置下载存放路径
                    .setDirectory(new File(LOCAL_REPO_PATH))
                    //设置权限验证
                    .setCredentialsProvider(provider)
                    .call();
        } catch (Exception e) {
            log.error("Git setupRepository error!------>" + e.getMessage());
        }
    }

    /**
     * 校验文件夹是否为空，为空则拉取数据
     *
     * @param path path
     */
    public static void checkAndChangeBranch(String path) {
        LOCAL_REPO_PATH = path;
        LOCAL_REPOGIT_CONFIG = LOCAL_REPO_PATH + "/.git";
        // 当前目录下
        File file = new File(path);
        //如果文件夹不存在
        if (!file.exists()) {
            //创建文件夹
            file.mkdirs();
        }
        if (file.isDirectory() && file.list().length == 0) {
            //设置目录
            setupRepository();
        }
        //拉取分支到本地
        checkout();
    }


}