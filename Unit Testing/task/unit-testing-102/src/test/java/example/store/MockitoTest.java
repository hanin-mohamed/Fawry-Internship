package example.store;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;
import org.mockito.internal.progress.ArgumentMatcherStorage;

import java.util.List;

public class MockitoTest {
    @Test
    void test() {
      List list= Mockito.mock(List.class);
      list.add("red");
      list.add("blue");
      list.add("green");
      System.out.println(list.get(0));
      Mockito.when(list.get(0)).thenReturn("black");
      Mockito.when(list.get(1)).thenThrow(new RuntimeException());
      System.out.println(list.get(0));
//     System.out.println(list.get(1));
     Mockito.verify(list).add("red");

//        Mockito.when(list.get(ArgumentMatcher.anyInt()))
//                .thenAnswer(invocationOnMock ->{
//                      int index=invocationOnMock.getArguments[0];
//                });


    }

}
