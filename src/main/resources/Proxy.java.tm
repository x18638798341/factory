public class Proxy0 extends Proxy implements UserDao {

    //第一步, 生成构造器
    protected Proxy0(InvocationHandler h) {
        super(h);
    }

    //第二步, 生成静态域
    private static Method m1;   //hashCode方法
    private static Method m2;   //equals方法
    private static Method m3;   //toString方法
    private static Method m4;   //...

    //第三步, 生成代理方法
    @Override
    public int hashCode() {
        try {
            return (int) h.invoke(this, m1, null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        try {
            Object[] args = new Object[] {obj};
            return (boolean) h.invoke(this, m2, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public String toString() {
        try {
            return (String) h.invoke(this, m3, null);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    @Override
    public void save(User user) {
        try {
            //构造参数数组, 如果有多个参数往后面添加就行了
            Object[] args = new Object[] {user};
            h.invoke(this, m4, args);
        } catch (Throwable e) {
            throw new UndeclaredThrowableException(e);
        }
    }

    //第四步, 生成静态初始化方法
    static {
        try {
            Class c1 = Class.forName(Object.class.getName());
            Class c2 = Class.forName(UserDao.class.getName());
            m1 = c1.getMethod("hashCode", null);
            m2 = c1.getMethod("equals", new Class[]{Object.class});
            m3 = c1.getMethod("toString", null);
            m4 = c2.getMethod("save", new Class[]{User.class});
            //...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}