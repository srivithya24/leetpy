import java.util.*;

class Solution {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

        Set<String> wordSet = new HashSet<>(wordList);

        // endWord must exist
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);

        int level = 1;

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                // Reached target
                if (word.equals(endWord)) {
                    return level;
                }

                char[] arr = word.toCharArray();

                // Change one character at a time
                for (int j = 0; j < arr.length; j++) {

                    char original = arr[j];

                    for (char c = 'a'; c <= 'z'; c++) {

                        arr[j] = c;

                        String newWord = new String(arr);

                        if (wordSet.contains(newWord) &&
                            !visited.contains(newWord)) {

                            queue.offer(newWord);
                            visited.add(newWord);
                        }
                    }

                    arr[j] = original;
                }
            }

            level++;
        }

        return 0;
    }
}
