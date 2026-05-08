import java.util.*;

class Solution {

    public List<List<String>> findLadders(String beginWord,
                                          String endWord,
                                          List<String> wordList) {

        List<List<String>> result = new ArrayList<>();

        Set<String> wordSet = new HashSet<>(wordList);

        if (!wordSet.contains(endWord)) {
            return result;
        }

        // Graph and distance map
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();

        bfs(beginWord, endWord, wordSet, graph, distance);

        List<String> path = new ArrayList<>();

        dfs(beginWord, endWord, graph, distance, path, result);

        return result;
    }

    // BFS to build shortest path graph
    private void bfs(String beginWord,
                     String endWord,
                     Set<String> wordSet,
                     Map<String, List<String>> graph,
                     Map<String, Integer> distance) {

        Queue<String> queue = new LinkedList<>();

        queue.offer(beginWord);

        distance.put(beginWord, 0);

        for (String word : wordSet) {
            graph.put(word, new ArrayList<>());
        }

        graph.put(beginWord, new ArrayList<>());

        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean found = false;

            for (int i = 0; i < size; i++) {

                String word = queue.poll();

                int currDist = distance.get(word);

                List<String> neighbors = getNeighbors(word, wordSet);

                for (String next : neighbors) {

                    graph.get(word).add(next);

                    if (!distance.containsKey(next)) {

                        distance.put(next, currDist + 1);

                        if (next.equals(endWord)) {
                            found = true;
                        }
                        else {
                            queue.offer(next);
                        }
                    }
                }
            }

            if (found) {
                break;
            }
        }
    }

    // DFS to build all shortest paths
    private void dfs(String curr,
                     String endWord,
                     Map<String, List<String>> graph,
                     Map<String, Integer> distance,
                     List<String> path,
                     List<List<String>> result) {

        path.add(curr);

        if (curr.equals(endWord)) {

            result.add(new ArrayList<>(path));
        }
        else {

            for (String next : graph.get(curr)) {

                if (distance.get(next) == distance.get(curr) + 1) {

                    dfs(next, endWord, graph,
                        distance, path, result);
                }
            }
        }

        path.remove(path.size() - 1);
    }

    // Generate neighbors
    private List<String> getNeighbors(String word,
                                      Set<String> wordSet) {

        List<String> neighbors = new ArrayList<>();

        char[] arr = word.toCharArray();

        for (int i = 0; i < arr.length; i++) {

            char original = arr[i];

            for (char c = 'a'; c <= 'z'; c++) {

                arr[i] = c;

                String newWord = new String(arr);

                if (wordSet.contains(newWord)
                    && !newWord.equals(word)) {

                    neighbors.add(newWord);
                }
            }

            arr[i] = original;
        }

        return neighbors;
    }
}
