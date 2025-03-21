package org.example.springfromscratchex.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example.springfromscratchex")
// 어느경로 기준으로 스캔할지, 본인 패키지명 중 가장 좁히는 범위
public class AppConfig {

}
