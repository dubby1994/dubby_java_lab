package cn.dubby.java.lab.java.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by yangzheng03 on 2018/1/22.
 */
public class FirstJavaAgentTest {

    public static void premain(String agentArgument, Instrumentation instrumentation) {
        System.out.println("Test Java Agent");
        SimpleClassTransformer transformer = new SimpleClassTransformer();
        instrumentation.addTransformer(transformer);
    }

}
