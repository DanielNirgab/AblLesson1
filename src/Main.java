import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        //Базовый уровень
        //Задача №1
        //Дано (их менять нельзя)
        String hi = "                Hello ";
        String world = " WoRld!";
        char newLine = '\n';
        //Создать из трех переменных единую строку,
        //Привести к правильному виду (Ниже)
        //затроить (Можно вызвать тольку одну команду System.out.print())
        //
        //Результат вывода на экран:
        //Hello world!
        //Hello world!
        //Hello world!
        for (int i = 0; i <3; i++) {
            System.out.print(hi.trim()+world.toLowerCase()+newLine);
        }

        //Задача №2
        //Создать переменные с ростом, весом.
        //Произвести расчет индекса массы тела (вес) / (рост * рост) используя переменные, вывести на экран
        //Пример результата вывода на экран:
        //21.0
        int weight = 60;
        double height = 1.86;
        double index = weight / (height*height);
        System.out.printf("Индекса массы тела: %.2f%n", index);

        //Задача №3
        //Создать из массива букв a,b,c,d,e, строку,вывести на экран , поменять в массиве 4 букву по счету на h,
        //и снова создать строку, вывести на экран
        String [] words = {"a","b","c","d","e"};
        System.out.println(Arrays.toString(words));
        words[3] = "h";
        System.out.println(Arrays.toString(words));

        //Продвинутый уровень
        System.out.println("Продвинутый уровень");
        //Задача №1
        //Произвести преобразование "234" в число типа int и прибавить к этому числу длину строки "some_text"
        String numbersStr = "234";
        String someText = "some_text";
        int numbers = Integer.parseInt(numbersStr);
        int sumOfIntegers = numbers + someText.length();
        System.out.println(sumOfIntegers);

        //Задача №2
        //Посчитать (a+b)^2 = ?, при a=3, b=5
        int a = 3;
        int b = 5;
        int sum = (int) Math.pow((a + b), 2);
        System.out.println(sum);

        //Задача №3
        //Создать два массив чисел:
        // 1,2,5,7,10
        // 2,3,2,17,15
        // Создать массив чисел, в котором будут: все числа из этих двух массивов,
        // и результат умножения чисел с одинаковым порядковым номером
        //
        //Ожидаемый результат:
        //1,2,5,7,10,2,3,2,17,15,2,6,10,119,150
        //(первый массив - 1,2,5,7,10), (второй массив - 2,3,2,17,15),
        //(результат перемножения - (1*2), (2*3), (5*2), (7*17), (10*15)
        int [] m1 = {1,2,5,7,10};
        int [] m2 = {2,3,2,17,15};
        int [] result = new int[m1.length*3];
        for (int i = 0; i < result.length; i++) {
            for (int j = i; j < m1.length; j++) {
                result[i] = m1[j];
                result[i + (m1.length)] = m2[j];
                result[i+(m1.length*2)] = m1[j]*m2[j];
                break;
            }
        }
        System.out.println(Arrays.toString(result));


        //Задача №4
        //В слове "Hello world!" заменить все l на r, сделать все буквы заглавными, выбрать первые 8 символов
        String helloWorld = "Hello world!";
        helloWorld = helloWorld.replace("l", "r").toUpperCase();
        System.out.println(helloWorld.substring(0,8));



        //Экспертный уровень
        System.out.println("Экспертный уровень");
        //Задача №1
        //Создать метод маскирования персональных данных, который:
        //Телефон (до/после маскирования): 79991113344 / 7999***3344
        //Email (до/после маскирования): test@yandex.ru / tes*@******.ru, my_mail@gmail.com / my_mai*@*****.com
        //Фио (до/после маскирования): Иванов Иван Иванович / И****в Иван И.
        //
        //Входящие параметры: String text
        //Возвращаемый результат: String
        //
        //Примеры возможного текста:
        //<client>(Какие то данные)<data>79991113344;test@yandex.ru;Иванов Иван Иванович</data></client> - "<client>(Какие то данные)<data>7999***3344;tes*@******.ru;И****в Иван И.</data></client>"
        //<client>(Какие то данные)<data></data></client> - вернет тоже (никаких ошибок)
        //<client>(Какие то данные)<data>Иванов Иван Иванович;79991113344</data></client> - "<client>(Какие то данные)<data>И****в Иван И.;7999***3344</data></client>"

        //Используемые технологии: String.find, String.replaceAll, String.split, String.join, String.contains, String.substring
        //Регулярные выражения, класс StringBuilder
        String [] client1 = {"79991113344", "test@yandex.ru", "Иванов Иван Иванович"};
        String [] client2 = {"my_mail@gmail.com", "Петров Петр Петрович", "79991113344"};

        System.out.println(dataMasking(client1));
        System.out.println(dataMasking(client2));

    }

    //String param1, String param2, String param3

    public static String dataMasking (String [] client) {
        String phone = null;
        String email = null;
        String fullName = null;

        for (int i = 0; i < client.length; i++) {
            if (client[i].matches("\\d*")) {
                phone = client[i];
            } else if (client[i].matches(".*\\w.*")) {
                email = client[i];
            } else if (client[i].matches(".*[А-я].*")) {
                fullName=client[i];
            } else {
                System.out.println("check info");
            }
        }

        StringBuilder phoneMask = new StringBuilder(phone);
        phoneMask.replace(4, 7, "***");

        StringBuilder emailMask = new StringBuilder(email);
        int ruIndex = emailMask.indexOf(".");
        if (email.contains(".ru")) {
            ruIndex = emailMask.indexOf(".ru");
        } else if (email.contains(".com")){
            ruIndex = emailMask.indexOf(".com");
        }
        int sIndex = emailMask.indexOf("@");
        emailMask.replace(sIndex+1, ruIndex, "*****");
        emailMask.replace(sIndex - 1, sIndex, "*");

        String [] name = fullName.split(" ");
        StringBuilder[] fullNameMask = new StringBuilder[name.length];
        for (int i = 0; i < fullNameMask.length; i++) {
            fullNameMask[i] = new StringBuilder(name[i]);
        }
        int lengthFullName = fullNameMask.length;
        fullNameMask[0].replace(1, fullNameMask[0].length()-1, "****");
        fullNameMask[lengthFullName - 1].replace(1, fullNameMask[lengthFullName - 1].length(), ".");
        System.out.println(fullNameMask[lengthFullName - 1]);

        String result = "<client>(Какие то данные)<data></data></client>";
        for (int i = 0; i < client.length; i++) {
            if (client[i] != null) {
                result = "Запутался...";
            }
        }
        return result;
    }
}