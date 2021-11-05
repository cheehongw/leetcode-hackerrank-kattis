/**
 * @author Wong Chee Hong (A0217558W)
 */
class PeaSoupAndPancakes {

    public static void main(String[] args) {
        FastIO fio = new FastIO();
        int restaurantNum = fio.nextInt();
        System.out.println(getRestaurantName(fio, restaurantNum));

        fio.close();

    }

    static String getRestaurantName(FastIO fio, int restaurantNum) {
        for (int i = 0; i < restaurantNum; i++) {
            int menuItems = fio.nextInt();
            String name = fio.nextLine();
            boolean peasoupFlag = false;
            boolean pancakeFlag = false;

            for (int j = 0; j < menuItems; j++) {
                String item = fio.nextLine();
                peasoupFlag = item.equals("pea soup") ? true : peasoupFlag;
                pancakeFlag = item.equals("pancakes") ? true : pancakeFlag;

                if (pancakeFlag && peasoupFlag) {
                    break;
                }
            }

            if (pancakeFlag && peasoupFlag) {
                return name;
            }

        }

        return "Anywhere is fine I guess";
    }
}