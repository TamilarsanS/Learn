package StreamLearn;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeated {
    public static void main(String[] args) {
        String input = "abcbaerteghyayhg";
        Map<Character, Long> collect = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        System.out.println(collect);
        Optional<Character> first = collect.entrySet().stream().filter(x -> x.getValue() == 1).map(Map.Entry::getKey).findFirst();
        System.out.println(first.isPresent()?first.get(): "All characters are repeated");
    }
}
