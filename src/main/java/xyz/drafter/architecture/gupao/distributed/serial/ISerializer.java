package xyz.drafter.architecture.gupao.distributed.serial;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public interface ISerializer {

    <T> byte[] serializer(T obj);

    <T> T deSerializer(byte[] data,Class<T> clazz);
}
