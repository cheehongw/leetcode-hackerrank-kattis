/**
 * @author Wong Chee Hong (A0217558W)
 */
public class Teque {

    private MyDeque firstHalf;
    private int sizeFirst;
    private MyDeque secondHalf;
    private int sizeSecond;

    public Teque() {
        this.firstHalf = new MyDeque();
        this.secondHalf = new MyDeque();
        this.sizeFirst = 0;
        this.sizeSecond = 0;
    }

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int N = fio.nextInt();

        Teque t = new Teque();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int instruction_length = fio.next().length();
            int argument = fio.nextInt();

            switch(instruction_length) {
                case 9:
                    t.push_back(argument);
                    break;
                case 10:
                    t.push_front(argument);
                    break;
                case 11:
                    t.push_mid(argument);
                    break;
                default:
                    sb.append(t.get_i(argument)).append("\n");
                    
            }
        }

        System.out.println(sb);

        fio.close();
    }

    public void push_front(int x) {
        firstHalf.offerFront(x);
        if (sizeFirst == sizeSecond) {
            sizeFirst++;
        } else { // sizeFirst > sizeSecond
            secondHalf.offerFront(firstHalf.pollBack());
            sizeSecond++;
        }
    }

    public void push_back(int x) {
        secondHalf.offerBack(x);
        if (sizeFirst == sizeSecond) {
            firstHalf.offerBack(secondHalf.pollFront());
            sizeFirst++;
        } else { // sizeSecond < sizeFirst
            sizeSecond++;
        }
    }

    public void push_mid(int x) {
        if (sizeFirst == sizeSecond) {
            firstHalf.offerBack(x);
            sizeFirst++;
        } else {
            secondHalf.offerFront(x);
            sizeSecond++;
        }

    }

    public int get_i(int idx) {
        if (idx <= sizeFirst - 1) {
            return firstHalf.get(idx);
        } else {
            return secondHalf.get(idx - sizeFirst);
        }

    }

}

/**
 * @author Wong Chee Hong (A0217558W)
 */
class MyDeque {

    int[] array = new int[524288];
    int front_ptr = 0;
    int back_ptr = 0;

    public int pollFront() {
        int target = array[front_ptr];
        this.front_ptr = (front_ptr + 1) % array.length;
        return target;
    }

    public int pollBack() {
        this.back_ptr = Math.floorMod((back_ptr - 1), array.length);
        int target = array[back_ptr];
        return target;
    }

    public void offerFront(int e) {
        if (this.isFull()) {
            this.resize();
        }

        this.front_ptr = Math.floorMod(front_ptr - 1, array.length);
        this.array[front_ptr] = e;
    }

    public void offerBack(int e) {
        if (this.isFull()) {
            this.resize();
        }

        this.array[back_ptr] = e;
        this.back_ptr = (back_ptr + 1) % array.length;
    }

    void resize() { // double the size of the array when array is full
        if (this.isFull()) {
            int[] newArray = new int[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[front_ptr];
                front_ptr = (front_ptr + 1) % array.length;
            }
            this.front_ptr = 0;
            this.back_ptr = array.length - 1;
            this.array = newArray;
        }
    }

    boolean isFull() {
        return ((back_ptr + 1) % array.length) == front_ptr;
    }

    public int get(int idx) {
        return array[(front_ptr + idx) % array.length];
    }

}
