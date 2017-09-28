package nos2jdbc.core.it;

import java.lang.reflect.Field;
import java.util.Map;

import javax.transaction.UserTransaction;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.manager.JdbcManagerImplementor;
import org.seasar.extension.jta.UserTransactionImpl;
import org.seasar.framework.util.OgnlUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;

import nos2jdbc.TransactionManagerRegistry;
import nos2jdbc.standalone.NoS2JdbcManager;

public class NoS2Jdbc extends BlockJUnit4ClassRunner {
    private JdbcManager jdbcManager;
    boolean isIgnored;

    public NoS2Jdbc(Class<?> klass) throws InitializationError {
	super(klass);
	String db = System.getProperty("database");
	if (StringUtil.isNotBlank(db))
	    jdbcManager = NoS2JdbcManager.getJdbcManager("nos2jdbc-datasource-" + db + ".properties");
	else
	    jdbcManager = NoS2JdbcManager.getJdbcManager();
	isIgnored = !getPrerequisiteBoolean(klass.getAnnotation(Prerequisite.class));
    }

    @Override
    public Object createTest() throws Exception {
	Object target = super.createTest();
	try {
	    Field field = target.getClass().getDeclaredField("jdbcManager");
	    field.setAccessible(true);
	    field.set(target, jdbcManager);
	} catch (ReflectiveOperationException e) {
	}
	try {
	    Field field = target.getClass().getDeclaredField("jdbcManagerImplementor");

	    field.setAccessible(true);
	    field.set(target, (JdbcManagerImplementor)jdbcManager);
	} catch (ReflectiveOperationException e) {
	}

	return target;
    }

        
    protected boolean getPrerequisiteBoolean(Prerequisite p) {
	if (p == null) return true;
	Map<String, Object> ctx = CollectionsUtil.newHashMap();
	ctx.put("ENV", System.getProperty("database"));
//	ctx.put("ENV", "hsqldb");
        Object exp = OgnlUtil.parseExpression(p.value());
        Object result =  OgnlUtil.getValue(exp, ctx, null);
        if (result instanceof Boolean && Boolean.class.cast(result)) {
            return true;
        }
        return false;
    }

    @Override
    protected boolean isIgnored(FrameworkMethod method) {
	if (isIgnored) return true;
	return !getPrerequisiteBoolean(method.getAnnotation(Prerequisite.class));
    }

    @Override
    public Statement methodInvoker(FrameworkMethod method, Object target) {
	return new MyInvokeMethod(method, target);
    }
    
    class MyInvokeMethod extends InvokeMethod {
	MyInvokeMethod(FrameworkMethod method, Object target) {
	    super(method, target);
	}
	
	@Override
	public void evaluate() throws Throwable {
	    UserTransaction tx = new UserTransactionImpl(TransactionManagerRegistry.get());
	    try {
		tx.begin();
		super.evaluate();
	    } finally {
		tx.rollback();
	    }
	}
    }

}
