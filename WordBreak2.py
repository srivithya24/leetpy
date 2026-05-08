class Solution:
    def wordBreak(self, s, wordDict):
        wordSet = set(wordDict)
        memo = {}

        def backtrack(start):
            # If already computed
            if start in memo:
                return memo[start]

            # Reached end of string
            if start == len(s):
                return [""]

            result = []

            for end in range(start + 1, len(s) + 1):
                word = s[start:end]

                if word in wordSet:
                    remaining = backtrack(end)

                    for sentence in remaining:
                        if sentence == "":
                            result.append(word)
                        else:
                            result.append(word + " " + sentence)

            memo[start] = result
            return result

        return backtrack(0)
