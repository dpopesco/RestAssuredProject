package localhost;

import org.testng.annotations.DataProvider;

public class DataForTests {

    @DataProvider(name = "DataForPost")
    public Object[][] dataForPost() {
      /*  Object[][] data = new Object[2][3];

        data[0][0] = "Rux";
        data[0][1] = "Elba";
        data[0][2] = 2;

        data[1][0] = "Alexandra";
        data[1][1] = "Miron";
        data[1][2] = 1;

        return data;*/

        return new Object[][]{
                {"Milo", "Docteur", 1},
                {"Mira", "Gerard", 2}
        };
    }

    @DataProvider(name = "Delete Data")
    public Object[][] dataForDelete() {
        return new Object[][]{
                {4}, {5}, {6}, {7}
        };
    }
}
