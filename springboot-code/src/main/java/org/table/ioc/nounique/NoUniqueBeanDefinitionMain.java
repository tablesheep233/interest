package org.table.ioc.nounique;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.google.common.collect.ImmutableMap;
import org.slf4j.LoggerFactory;
import org.table.ioc.nounique.autowirecandidate.AutowireCandidateExecutor;
import org.table.ioc.nounique.autowiredbybeanname.AutowiredByBeanNameExecutor;
import org.table.ioc.nounique.error.ErrorExecutor;
import org.table.ioc.nounique.primary.PrimaryExecutor;
import org.table.ioc.nounique.priority.PriorityExecutor;
import org.table.ioc.nounique.qualifier.QualifierExecutor;

import java.util.LinkedHashMap;
import java.util.Map;

public class NoUniqueBeanDefinitionMain {

    public static Map<String, NoUniqueBeanDefinitionExecutor> EXECUTORS;

    private static final String ERROR = "ERROR";
    private static final String AUTOWIRECANDIDATE = "AUTOWIRECANDIDATE";
    private static final String QUALIFIER = "QUALIFIER";
    private static final String PRIORITY = "PRIORITY";
    private static final String PRIMARY = "PRIMARY";
    private static final String AUTOBYNAME = "AUTOBYNAME";

    static {
        Map<String, NoUniqueBeanDefinitionExecutor> executorMap = new LinkedHashMap<>();
        executorMap.put(ERROR, new ErrorExecutor());
        executorMap.put(AUTOWIRECANDIDATE, new AutowireCandidateExecutor());
        executorMap.put(QUALIFIER, new QualifierExecutor());
        executorMap.put(PRIORITY, new PriorityExecutor());
        executorMap.put(PRIMARY, new PrimaryExecutor());
        executorMap.put(AUTOBYNAME, new AutowiredByBeanNameExecutor());
        EXECUTORS = ImmutableMap.copyOf(executorMap);
    }

    public static void main(String[] args) {
        setLogLevel();
        for (NoUniqueBeanDefinitionExecutor executor : EXECUTORS.values()) {
            try {
                executor.execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void setLogLevel() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        for (Logger logger : loggerContext.getLoggerList()) {
            logger.setLevel(Level.INFO);
        }
    }
}
