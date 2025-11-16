public class TestClass {
    
    // Публичные методы
    @Invoke(2)
    public void publicMethod1(String message) {
        System.out.println("Public Method 1: " + message);
    }
    
    public void publicMethod2(int number) {
        System.out.println("Public Method 2: " + number);
    }
    
    // Защищенные методы
    @Invoke(3)
    protected void protectedMethod1(String text) {
        System.out.println("Protected Method 1: " + text);
    }
    
    @Invoke(1)
    protected void protectedMethod2() {
        System.out.println("Protected Method 2 (no params)");
    }
    
    // Приватные методы
    @Invoke(2)
    private void privateMethod1(int a, int b) {
        System.out.println("Private Method 1: " + a + " + " + b + " = " + (a + b));
    }
    
    private void privateMethod2(String s) {
        System.out.println("Private Method 2: " + s);
    }
}