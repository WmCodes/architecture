package xyz.drafter.architecture.gupao.distributed.serial;

/**
 * @author wangmeng
 * @date 2019/9/19
 * @desciption
 */
public class Test {

   public static void main(String[] args) {
        ISerializer iSerializer = new JavaSerializer();

        // 静态变量不会进行序列化
       // 属性前面增加  transient 也不会被序列化

       // 父类若没实现序列化，则子类中父类的属性不会被序列化

       // 若父类实现序列化，子类可不实现序列化，具有传递性

        User user = new User();
        user.setAge(12);
        user.setName("MIC");

        byte[] byteUser = iSerializer.serializer(user);

        User user1 = iSerializer.deSerializer(byteUser, User.class);

        System.out.println(user1);
    }

/*    public static void init(int n){
        int[] arr = new int[n];
        for (int i = 0;i<n;i++){
            arr[i] = i+1;
        }
        List<Integer> list=new ArrayList<Integer>();
        find(list, arr, n, 0);
    }

    public static void find(List<Integer> tmp,int[] arr, int n,int num){
        int sums = tmp.stream().mapToInt(Integer::intValue).sum();
        if(sums == n){
            if (tmp.size()>1) {
                System.out.println(n + "=" + tmp.stream().map(Object::toString).collect(Collectors.joining("+")));
            }
            return;
        }
        for(int i=num;i<arr.length&&arr[i]<=(n-sums);i++){
            List<Integer> list = new ArrayList<>(tmp);
            list.add(arr[i]);
            find(list,arr,n,i);
        }
    }

    public static void main(String[] args) {
        init(4);

    }*/


}
