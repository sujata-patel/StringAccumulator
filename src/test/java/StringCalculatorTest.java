import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringCalculatorTest {
    StringCalculator stringCalculator;

    @Before
    public void setUp() throws Exception {
        stringCalculator = new StringCalculator();
    }

    @Test
    public void test_add_should_return_zero_for_empty_string() throws Exception {

        int sum = stringCalculator.add("");

        assertThat(sum, is(0));
    }

    @Test(expected = Exception.class)
    public void test_add_should_throw_exception() throws Exception {

        int sum = stringCalculator.add(null);

    }

    @Test
    public void test_add_should_return_1_for_string_1() throws Exception {

        int sum = stringCalculator.add("1");

        assertThat(sum, is(1));
    }

    @Test
    public void test_add_should_return_2_for_string_2() throws Exception {

        int sum = stringCalculator.add("2");

        assertThat(sum, is(2));
    }

    @Test
    public void test_add_should_return_3_for_string_12() throws Exception {

        int sum = stringCalculator.add("1,2");

        assertThat(sum, is(3));
    }

    @Test
    public void test_add_should_return_10_for_string_1234() throws Exception {

        int sum = stringCalculator.add("1,2,3,4");

        assertThat(sum, is(10));
    }

    @Test
    public void test_add_should_return_10_for_string_1_newLine2_comma3_comma4() throws Exception {

        int sum = stringCalculator.add("1\n2,3,4");

        assertThat(sum, is(10));
    }

    @Test
    public void test_isDelimiterPassed_should_return_true() {

        boolean delimiterPassed = stringCalculator.isDelimiterPassed("//;\n1");

        assertTrue(delimiterPassed);
    }

    @Test
    public void test_isDelimiterPassed_should_return_false() {

        boolean delimiterPassed = stringCalculator.isDelimiterPassed("1");

        assertFalse(delimiterPassed);
    }

    @Test
    public void test_add_should_return_sum_of_numbers_separated_by_passed_delim() throws Exception {

        int sum = stringCalculator.add("//;\n1;2;3;4");

        assertThat(sum, is(10));
    }

    @Test
    public void test_add_should_return_throw_exception_when_negative_number_passed() throws Exception {

        try {
            int sum = stringCalculator.add("1,-2");
            fail();
        }catch (Exception e){
            assertThat(e.getMessage(), is("negatives not allowed - -2"));
        }
    }

    @Test
    public void test_add_should_return_throw_exception_when_negative_numbers_passed() throws Exception {

        try {
            int sum = stringCalculator.add("1,-2,-4");
            fail();
        }catch (Exception e){
            assertThat(e.getMessage(), is("negatives not allowed - -2-4"));
        }
    }

    @Test
    public void test_add_should_return_10_for_string_12100234() throws Exception {

        int sum = stringCalculator.add("1,2,1002,3,4");

        assertThat(sum, is(10));
    }

    @Test
    public void test_add_should_return_sum_of_numbers_separated_by_passed_delim_of_length3() throws Exception {

        int sum = stringCalculator.add("//***\n1***2***3***4");

        assertThat(sum, is(10));
    }

    @Test
    public void test_add_should_return_sum_of_numbers_separated_by_passed_multiple_delim() throws Exception {

        int sum = stringCalculator.add("//***|$$\n1***2***3***4");

        assertThat(sum, is(10));
    }


}
