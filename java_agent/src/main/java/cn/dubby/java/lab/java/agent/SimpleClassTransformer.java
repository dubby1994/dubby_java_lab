package cn.dubby.java.lab.java.agent;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.tree.ClassNode;
import jdk.internal.org.objectweb.asm.tree.MethodNode;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * Created by yangzheng03 on 2018/1/22.
 */
public class SimpleClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        System.out.println(className);
        System.out.println("Processing class " + className);

        String normalizedClassName = className.replaceAll("/", ".");

        ClassReader classReader = null;
        try {
            classReader = new ClassReader(normalizedClassName);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ClassNode classNode = new ClassNode();
        classReader.accept(classNode, ClassReader.SKIP_DEBUG);

        @SuppressWarnings("unchecked")
        List<MethodNode> allMethods = classNode.methods;
        for (MethodNode methodNode : allMethods) {
            System.out.println(methodNode.name);
        }
        return classfileBuffer;
    }
}
