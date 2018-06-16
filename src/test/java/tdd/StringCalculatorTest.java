package tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {
    @Test
    public void shouldReturnZeroWhenDataIsEmpty() {
        //given
        String dataZero = "";
        int expectedResilt = 0;
        //when
        int accualResult = StringCalculator.addint(dataZero);
        //then
        Assertions.assertEquals(expectedResilt, accualResult);
    }

    @Test
    public void shouldReturnZeroWhenDataIsBlank() {
        //given
        String dataZero = "";
        int expectedResilt = 0;
        //when
        int accualResult = StringCalculator.addint(dataZero);
        //then
        Assertions.assertEquals(expectedResilt, accualResult);
    }

    @Test
    void shouldReturnNumberWhenDataIsOneNumber() {
        //given
        String data1 = "1";
        String data2 = "  7 ";
        String data3 = "  5";

        int expectedResult = 1;
        int expectedResult2 = 7;
        int expectedResult3 = 5;
        //when
        int actualResult1 = StringCalculator.addint(data1);
        int actualResult2 = StringCalculator.addint(data2);
        int actualResult3 = StringCalculator.addint(data3);
        //then
        Assertions.assertEquals(expectedResult, actualResult1);
        Assertions.assertEquals(expectedResult2, actualResult2);
        Assertions.assertEquals(expectedResult3, actualResult3);
    }

    @Test
    void shouldThrowsExpetionWhenLetter() {
        //given
        String data = "a ";
        //when
        try {
            int actualResult = StringCalculator.addint(data);
            fail("Didn't throw Exception");
        } catch (Exception e) {
            Assertions.assertEquals(IllegalArgumentException.class, e.getClass());
            Assertions.assertNotEquals(NumberFormatException.class,e.getClass());
        }
    }@Test
    void shouldReturnForTwoDigitsWithComma() {
        //given
        String data = "1, 2,4,23";
        int expectedResult = 30;
        //when
        int accualResult =StringCalculator.addint(data);
        //then
        Assertions.assertEquals(expectedResult,accualResult);

    }

    @Test
    void shouldReturnSumForManyDigitisSeparatorWithCustomDelimiter(){
        //given
        String data = "//****\n1****2****4****23";
        String data1 = "//;\n1;2;4;23";
        int expectedResult =30;
        int expectedResult2 =30;

        //when
        int accualResult = StringCalculator.addint(data);
        int accualResult2 = StringCalculator.addint(data1);
        //then
        Assertions.assertEquals(expectedResult,accualResult);
        Assertions.assertEquals(expectedResult2,accualResult2);

    }

    @Test
    void shouldTrhowExeptionWhenNegativeNumber() {
        //given
        String data = "-3";

        //when

        Assertions.assertThrows(IllegalArgumentException.class,
                ()->StringCalculator.addint(data));
    }
}