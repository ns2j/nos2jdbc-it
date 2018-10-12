package nos2jdbc.core.it;

import java.lang.reflect.Field;
import java.util.Map;
import javax.transaction.UserTransaction;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
/*
import org.junit.jupiter.api.runner.internal.runners.statements.InvokeMethod;
import org.junit.jupiter.api.runner.runners.BlockJUnit4ClassRunner;
import org.junit.jupiter.api.runner.runners.model.FrameworkMethod;
import org.junit.jupiter.api.runner.runners.model.InitializationError;
import org.junit.jupiter.api.runner.runners.model.Statement;
*/
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.manager.JdbcManagerImplementor;
import org.seasar.extension.jta.UserTransactionImpl;
import org.seasar.framework.util.OgnlUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;
import nos2jdbc.TransactionManagerRegistry;
import nos2jdbc.standalone.NoS2JdbcManager;

public class NoS2JdbcExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private JdbcManager jdbcManager;
    UserTransaction tx;
    
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String db = System.getProperty("database");
        if (StringUtil.isNotBlank(db))
            jdbcManager = NoS2JdbcManager.getJdbcManager("nos2jdbc-datasource-" + db + ".properties");
        else
            jdbcManager = NoS2JdbcManager.getJdbcManager();

    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        Class<?> clazz = context.getRequiredTestClass();
        Object instance = context.getRequiredTestInstance();
        try {
            Field field = clazz.getDeclaredField("jdbcManager");
            field.setAccessible(true);
            field.set(instance, jdbcManager);
        } catch (ReflectiveOperationException e) {

        }
        try {
            Field field = clazz.getDeclaredField("jdbcManagerImplementor");

            field.setAccessible(true);
            field.set(instance, (JdbcManagerImplementor)jdbcManager);
        } catch (ReflectiveOperationException e) {
        }
        
        tx = new UserTransactionImpl(TransactionManagerRegistry.get());
	tx.begin();
	
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
	tx.rollback();
    }

}

