/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labirent1;

import java.util.Scanner;

/**
 *
 * @file ProjeLabirent
 * @descriptionJava programlama dili kullanılarak geliştirilecek metin tabanlı labirent keşif oyununda oyuncu,
 * 2B bir karakter matrisi üzerinde engellerle karşılaşarak başlangıç noktasından bitiş noktasına en az hamlede ulaşmaya çalışacak.
 * @assignment güz dönemi birinci proje
 * @date 13.12.2023
 * @author Veranur Durmuş / veranur.durmus@stu.fsm.edu.tr
 */
public class ProjeLabirent {

    public static void main(String[] args) { 
        char[][] labirent = {
            {'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
            {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
            {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
            {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
            {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
            {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
            {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
            {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
            {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
            {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
            {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
            {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
            {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
            {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};

        Scanner input = new Scanner(System.in);
        String adim = "Adım Sayısı: ";
        String konum = "Bulunduğunuz konum: ";
        String tanim = "W, A, S, D karakterlerinden birini giriniz. "
                + "\nBonus kullanmak için + karakterine basınız. "
                + "\nÇıkış için “exit” yazınız.";
        int[] adimsayisi = new int[1];
        int[] hareketsayisi = new int[1];
        int[] sonKonum = {baslangic(labirent), 0};
        int[] TBonus = new int[1];
        int[] RBonus = new int[1];
        int[] HBonus = new int[1];
        int[] FBonus = new int[1];

        while (true) {
            System.out.println("Labirent Oyunu: ");
            labirentYazdirma(labirent, sonKonum);
            System.out.println(adim + adimsayisi[0]);
            System.out.println(konum + "( " + sonKonum[0] + " , " + sonKonum[1] + ")");

            System.out.println(tanim);
            String girdi = input.next();
            System.out.println("Girdi: " + girdi);
            hareket(labirent, girdi, sonKonum, adimsayisi, TBonus, RBonus, HBonus, FBonus, hareketsayisi);
            bonusToplama(TBonus, RBonus, HBonus, FBonus, sonKonum, labirent);
            Mayin(FBonus, sonKonum, labirent, adimsayisi, TBonus, RBonus, HBonus, hareketsayisi, girdi);
            if (hareketsayisi[0] == 5) {
                LabirentDegistirme(labirent, hareketsayisi);
            }
            if (girdi == "+") {
                bonusYazdirma(TBonus, RBonus, HBonus, FBonus, sonKonum, labirent, adimsayisi, hareketsayisi, girdi);
            }
            if ("exit".equals(girdi.toLowerCase())) {
                System.out.println("Çıkış Yaptınız!");
                break;
            }
            if (sonKonum[0] == bitis(labirent) && sonKonum[1] == labirent[0].length - 1) {
                System.out.println("Çıkışa Ulaştınız! Oyun Bitti :)");
                System.out.println("Toplam adım sayısı: " + adimsayisi[0]);
                break;
            }

        }

    }

    public static void LabirentDegistirme(char[][] labirent, int[] hareketsayisi) {
        for (int row = 0; row < labirent.length; row++) {
            for (int col = 0; col < labirent[row].length; col++) {
                while (labirent[row][col] == '!') {
                    int row1 = (int) (Math.random() * labirent.length);
                    int col1 = (int) (Math.random() * labirent[row].length);
                    char temp = labirent[row1][col1];
                    if (temp == 'B' || temp == 'E' || temp == '#' || temp == 'T' || temp == 'F' || temp == 'R' || temp == 'H' || temp == '?') {
                        break;
                    } else {
                        labirent[row][col] = labirent[row1][col1];
                        labirent[row1][col1] = '!';
                        break;
                    }
                }
                while (labirent[row][col] == 'T' || labirent[row][col] == 'F' || labirent[row][col] == 'R' || labirent[row][col] == 'H') {
                    char ilk = labirent[row][col];
                    int row1 = (int) (Math.random() * labirent.length);
                    int col1 = (int) (Math.random() * labirent[row].length);
                    char temp = labirent[row1][col1];
                    if (temp == 'B' || temp == 'E' || temp == '#' || temp == '!' || temp == '?') {
                        break;
                    } else {
                        labirent[row][col] = labirent[row1][col1];
                        labirent[row1][col1] = ilk;
                        break;
                    }
                }
            }
        }
        System.out.println("Harita güncellendi.");
        hareketsayisi[0] = 0;
    }

    public static void Duvar(int[] sonKonum, char[][] labirent, int[] RBonus, int[] FBonus, int[] HBonus, int[] TBonus, int[] adimsayisi, int[] hareketsayisi, String girdi) {
        Scanner input = new Scanner(System.in);
        System.out.println("Engel ile karşılaştınız. ");
        while (RBonus[0] >= 0) {
            if (RBonus[0] == 0) {
                System.out.println("Elinizde engel kaldırma bonusu bulunmamaktadır. \nBaşka yöne gitmeyi deneyin.");
                break;
            }
            System.out.println("Engel kaldırma bonusuna bakmak için + basınız. \nBonus kullanmak istemiyorsanız * basınız.");
            String karakter = input.nextLine();
            if ("+".equals(karakter)) {
                bonusYazdirma(TBonus, RBonus, HBonus, FBonus, sonKonum, labirent, adimsayisi, hareketsayisi, girdi);
                break;
            } if("*".equals(karakter)){
                System.out.println("Bonus kullanmadığınız için başka yöne gitmeyi deneyin.");
                break;
            }
            else {
                System.out.println("Geçerli karakter giriniz:");
            }
        }

    }

    public static void Mayin(int[] FBonus, int[] sonKonum, char[][] labirent, int[] adimsayisi, int[] TBonus, int[] RBonus, int[] HBonus, int[] hareketsayisi, String girdi) {
        Scanner input = new Scanner(System.in);
        int a = sonKonum[0];
        int b = sonKonum[1];
        if (labirent[a][b] == '!') {
            System.out.println("Mayın ile karşılaşıldı.");
            if (FBonus[0] > 0) {
                while (true) {
                    System.out.println("Mayın çözme bonusuna bakmak için + basınız. \nBonus kullanmak istemiyorsanız * basınız.");
                    String karakter = input.nextLine();
                    if ("+".equals(karakter)) {
                        bonusYazdirma(TBonus, RBonus, HBonus, FBonus, sonKonum, labirent, adimsayisi, hareketsayisi, girdi);
                        break;
                    }
                    if ("*".equals(karakter)) {
                        System.out.println("Mayın patladı. ");
                        adimsayisi[0] += 5;
                        labirent[a][b] = '.';
                        System.out.println("adım sayınız arttırıldı.");
                        break;
                    } else {
                        System.out.println("Yanlış karakter girdiniz. ");
                    }
                }
            } else {
                System.out.println("Elinizde mayın çözme bonusu bulunmamaktır. \nMayın patladı.");
                adimsayisi[0] += 5;
                labirent[a][b] = '.';
                System.out.println("adım sayınız arttırıldı.");
            }
        }
    }

    public static void bonusToplama(int[] TBonus, int[] RBonus, int[] HBonus, int[] FBonus, int[] sonKonum, char[][] labirent) {
        int a = sonKonum[0];
        int b = sonKonum[1];
        if (labirent[a][b] == 'T') {
            System.out.println("T karakteri ile karşılaştınız, bonus listesine eklendi.");
            labirent[a][b] = '.';
            TBonus[0]++;
        }
        if (labirent[a][b] == 'R') {
            System.out.println("R karakteri ile karşılaştınız, bonus listesine eklendi.");
            labirent[a][b] = '.';
            RBonus[0]++;
        }
        if (labirent[a][b] == 'H') {
            System.out.println("H karakteri ile karşılaştınız, bonus listesine eklendi.");
            labirent[a][b] = '.';
            HBonus[0]++;
        }
        if (labirent[a][b] == 'F') {
            System.out.println("F karakteri ile karşılaştınız, bonus listesine eklendi.");
            labirent[a][b] = '.';
            FBonus[0]++;
        }

    }

    public static void bonusYazdirma(int[] TBonus, int[] RBonus, int[] HBonus, int[] FBonus, int[] sonKonum, char[][] labirent, int[] adimsayisi, int[] hareketsayisi, String girdi) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Kullanmak istediğin bonusu seç (T, R, H, F): \nT ışınlanma bonusundan " + TBonus[0] + " tane var. "
                    + "\nR engel kaldırma bonusundan " + RBonus[0] + " tane var. "
                    + "\nH hamle sayısı azaltma bonusundan " + HBonus[0] + " tane var. "
                    + "\nF mayın çözme bonusundan " + FBonus[0] + " tane var.\n"
                    + "Bonus kullanmak istemiyorsanız * basın. ");
            String girdi1 = input.nextLine();

            switch (girdi1.toUpperCase()) {
                case "T":
                    if (TBonus[0] > 0) {
                        TBonus(sonKonum, labirent, TBonus);
                        return;
                    } else {
                        System.out.println("T bonusu dizi içerisinde yok yeniden bonus giriniz: ");
                    }
                    break;
                case "R":
                    if (RBonus[0] > 0) {
                        RBonus(TBonus, RBonus, HBonus, FBonus, sonKonum, labirent, adimsayisi, hareketsayisi, girdi);
                        return;
                    } else {
                        System.out.println("R bonusu dizi içerisinde yok yeniden bonus giriniz: ");
                    }
                    break;
                case "H":
                    if (HBonus[0] > 0) {
                        HBonus(adimsayisi, HBonus);
                        return;
                    } else {
                        System.out.println("H bonusu dizi içerisinde yok yeniden bonus giriniz: ");
                    }
                    break;
                case "F":
                    if (FBonus[0] > 0) {
                        FBonus(FBonus, labirent, sonKonum);
                        return;
                    } else {
                        System.out.println("F bonusu dizi içerisinde yok yeniden bonus giriniz: ");
                    }
                    break;
                case "*":
                    return;
                default:
                    System.out.println("Geçersiz Harf. Ltfen T R H F harflerinden birini giriniz.");

            }
        }

    }

    public static void TBonus(int[] sonKonum, char[][] labirent, int[] TBonus) {
        Scanner input = new Scanner(System.in);
        System.out.println("T bonusu seçildi. Gidilmek istenen x ve y noktalarını giriniz: ");
        while (true) {
            System.out.print("satır: ");
            int x = input.nextInt();
            System.out.print("sütun: ");
            int y = input.nextInt();
            if (x < 0 || x >= labirent.length || y < 0 || y >= labirent[0].length) {
                System.out.println("Girilen kordinatlar labirentin dışında. Tekrar x, y noktalarını giriniz: ");
                continue;
            }
            if (labirent[x][y] == '!') {
                System.out.println("Işınlanmak istenilen noktada mayın var. Tekrar x, y noktalarını giriniz: ");
            } else if (labirent[x][y] == '#') {
                System.out.println("Işınlanmak istenilen noktada duvar var. Tekrar x, y noktalarını giriniz: ");
            } else {
                sonKonum[0] = x;
                sonKonum[1] = y;
                System.out.println("İstediğiniz konuma ışınlanıldı.");
                break;
            }
        }
        TBonus[0]--;
    }

    public static void RBonus(int[] TBonus, int[] RBonus, int[] HBonus, int[] FBonus, int[] sonKonum, char[][] labirent, int[] adimsayisi, int[] hareketsayisi, String girdi) {
        switch (girdi.toUpperCase()) {
            case "W":
                labirent[sonKonum[0] - 1][sonKonum[1]] = '.';
                sonKonum[0]--;
                System.out.println("Engel kaldırıldı.");
                RBonus[0]--;

                break;
            case "A":

                labirent[sonKonum[0]][sonKonum[1] - 1] = '.';
                sonKonum[1]--;
                System.out.println("Engel kaldırıldı.");
                RBonus[0]--;

                break;
            case "D":

                labirent[sonKonum[0]][sonKonum[1] + 1] = '.';
                sonKonum[1]++;
                System.out.println("Engel kaldırıldı.");
                RBonus[0]--;

                break;
            case "S":

                labirent[sonKonum[0] + 1][sonKonum[1]] = '.';
                sonKonum[0]++;
                System.out.println("Engel kaldırıldı.");
                RBonus[0]--;

                break;
            default:
                System.out.println("Engele gelmediğiniz için engel kaldırma bonusunu kullanamazsınız. ");
        }

    }

    public static void HBonus(int[] adimsayisi, int[] HBonus) {
        if (adimsayisi[0] - 2 >= 0) {
            adimsayisi[0] -= 2;
            HBonus[0]--;
            System.out.println("H bonusu kullanıldı. Hamle sayınız 2 azaltıldı. ");
        } else {
            System.out.println("Hamle sayısı sıfın altına düştüğü azaltılamadı. Bonusunuzu daha sonra kullanmayı deneyin.");
        }

    }

    public static void FBonus(int[] FBonus, char[][] labirent, int[] sonKonum) {
        int a = sonKonum[0];
        int b = sonKonum[1];
        if (labirent[a][b] == '!') {
            System.out.println("Mayın çözüldü.");
            labirent[a][b] = '.';
            FBonus[0]--;
        } else {
            System.out.println("Mayınla karşılaşmadığınız için mayın bonusu kullanılamaz.");
        }

    }

    public static int baslangic(char[][] matrix) {
        int baslangic = 0;
        for (int col = 0; col < matrix[0].length; col++) {
            if (matrix[col][0] == 'B') {
                baslangic = col;
            }
        }
        return baslangic;
    }

    public static int bitis(char[][] matrix) {
        int bitis = 0;
        int col = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][col] == 'E') {
                bitis = i;
            }
        }
        return bitis;
    }

    public static void labirentYazdirma(char[][] matrix, int[] sonKonum) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

                if (row == sonKonum[0] && col == sonKonum[1]) {
                    System.out.print("? ");
                } else {
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println("");
        }

    }

    public static void hareket(char[][] matrix, String girdi, int[] sonKonum, int[] adimsayisi, int[] TBonus, int[] RBonus, int[] HBonus, int[] FBonus, int[] hareketsayisi) {

        switch (girdi.toUpperCase()) {
            case "W":
                if (matrix[sonKonum[0] - 1][sonKonum[1]] == '#') {
                    Duvar(sonKonum, matrix, RBonus, FBonus, HBonus, TBonus, adimsayisi, hareketsayisi, girdi);
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                    break;
                }
                if (sonKonum[0] >= 0) {
                    sonKonum[0]--;
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                }
                if (sonKonum[0] < 0 || sonKonum[1] < 0) {
                    sonKonum[0]++;
                    adimsayisi[0]--;
                    System.out.println("Lütfen labirent dışına çıkmayınız. Tekrar karakter giriniz.");
                }

                break;
            case "A":
                if (matrix[sonKonum[0]][sonKonum[1] - 1] == '#') {
                    Duvar(sonKonum, matrix, RBonus, FBonus, HBonus, TBonus, adimsayisi, hareketsayisi, girdi);
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                    break;
                }
                if (sonKonum[1] >= 0) {
                    sonKonum[1]--;
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                }
                if (sonKonum[0] < 0 || sonKonum[1] < 0) {
                    System.out.println("Lütfen labirent dışına çıkmayınız. Tekrar karakter giriniz.");
                    sonKonum[1]++;
                    adimsayisi[0]--;
                }
                break;
            case "D":
                if (matrix[sonKonum[0]][sonKonum[1] + 1] == '#') {
                    Duvar(sonKonum, matrix, RBonus, FBonus, HBonus, TBonus, adimsayisi, hareketsayisi, girdi);
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                    break;
                }
                if (sonKonum[1] <= matrix[0].length - 1) {
                    sonKonum[1]++;
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                }
                if (sonKonum[0] > matrix[0].length - 1 || sonKonum[1] > matrix[0].length - 1) {
                    System.out.println("Lütfen labirent dışına çıkmayınız. Tekrar karakter giriniz.");
                    sonKonum[1]--;
                    adimsayisi[0]--;
                }
                break;
            case "S":
                if (matrix[sonKonum[0] + 1][sonKonum[1]] == '#') {
                    Duvar(sonKonum, matrix, RBonus, FBonus, HBonus, TBonus, adimsayisi, hareketsayisi, girdi);
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                    break;
                }
                if (sonKonum[0] <= matrix.length - 1) {
                    sonKonum[0]++;
                    adimsayisi[0]++;
                    hareketsayisi[0]++;
                }
                if (sonKonum[0] > matrix[0].length - 1 || sonKonum[1] > matrix[0].length - 1) {
                    System.out.println("Lütfen labirent dışına çıkmayınız. Tekrar karakter giriniz.");
                    sonKonum[0]--;
                    adimsayisi[0]--;
                }
                break;
            case "+":
                bonusYazdirma(TBonus, RBonus, HBonus, FBonus, sonKonum, matrix, adimsayisi, hareketsayisi, girdi);
                break;
            default:
                System.out.println("Geçersiz Harf Lütfen Tekrar Deneyiniz!");

        }

    }
    
}
