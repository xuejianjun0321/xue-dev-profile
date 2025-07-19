package com.ilabservice.cloud.sdk.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: jj.xue
 * @createTime: 2022年11月23日 16:58:19
 * @Description: maven 命令工具类
 */
@Slf4j
public class MavenUtils {

    /**
     * 通过Maven骨架生成新的应用  执行本工具类 必须本地安装Maven 执行mvn -v 可以检查
     * @param groupId 包路径
     * @param artifactId 应用名
     * @param directory 输出目录
     */
    public static void generateApplication(String groupId, String artifactId, String directory) {

        String command = "mvn archetype:generate -DarchetypeGroupId=com.ils " +
                "-DarchetypeArtifactId=ilabservice-project-archetype " +
                "-DarchetypeVersion=1.0.0-SNAPSHOT " +
                "-DoutputDirectory=" + directory +
                " -DgroupId=" + groupId +
                " -DartifactId=" + artifactId +
                " -Dversion=1.0.0-SNAPSHOT " +
                "-DinteractiveMode=false " +
                "-DarchetypeCatalog=local";
        try {
            boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
            ProcessBuilder builder = new ProcessBuilder();
            builder.redirectErrorStream(true);
            if (isWindows) {
                builder.command("cmd.exe", "/c", command);
            } else {
                builder.command("sh", "-c", command);
            }
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            int exitCode = process.waitFor();
            log.info("\nExited with error code : " + exitCode);
        } catch (Exception e) {
            log.error("通过Maven骨架生成新的应用发生异常 e={}", e);
        }
    }


}
