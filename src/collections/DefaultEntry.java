package collections;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-05-29
 * time        : 16:37
 * description : 默认条目
 */
public class DefaultEntry implements Entry {

    protected Object key;
    protected Object value;

    /******************************* 构造方法 **************************************/
    public DefaultEntry(Object k, Object v) {
        key = k;
        value = v;
    }

    /******************************* Entry 接口方法 **************************************/
    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object setKey(Object k) {
        Object oldKey = key;
        key = k;
        return oldKey;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object v) {
        Object oldValue = value;
        value = v;
        return oldValue;
    }
}
