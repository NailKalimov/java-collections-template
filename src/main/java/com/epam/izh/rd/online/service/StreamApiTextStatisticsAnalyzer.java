package com.epam.izh.rd.online.service;

import com.epam.izh.rd.online.helper.Direction;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.*;

/**
 * Данный класс обязан использовать StreamApi из функционала Java 8. Функциональность должна быть идентична
 * {@link SimpleTextStatisticsAnalyzer}.
 */
public class StreamApiTextStatisticsAnalyzer implements TextStatisticsAnalyzer {
    @Override
    public int countSumLengthOfWords(String text) {
        return getWords(text).stream().mapToInt(String::length).sum();
    }

    @Override
    public int countNumberOfWords(String text) {
        return getWords(text).stream().mapToInt(s->1).sum();
    }

    @Override
    public int countNumberOfUniqueWords(String text) {
        return getWords(text).stream().distinct().mapToInt(s->1).sum();
    }

    @Override
    public List<String> getWords(String text) {
        return Stream.of(text.split("\\P{Alpha}+")).collect(Collectors.toList());
    }

    @Override
    public Set<String> getUniqueWords(String text) {
        return getWords(text).stream().collect(Collectors.toSet());
    }

    @Override
    public Map<String, Integer> countNumberOfWordsRepetitions(String text) {
        return getWords(text).stream().collect(Collectors.toMap(s -> s, s->1, Integer::sum));
    }

    @Override
    public List<String> sortWordsByLength(String text, Direction direction) {
        return getWords(text).stream().sorted((o1, o2) -> direction==Direction.ASC? o1.length()-o2.length() :
                -(o1.length()-o2.length())).collect(Collectors.toList());
    }
}
