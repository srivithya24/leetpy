class Solution:

    def getSkyline(self, buildings):

        events = []

        # Create start and end events
        for left, right, height in buildings:

            # Start event -> negative height
            events.append((left, -height))

            # End event -> positive height
            events.append((right, height))

        # Sort events
        events.sort()

        import heapq
        from collections import Counter

        # Max heap using negative values
        heap = [0]

        # Store active heights
        active = Counter({0: 1})

        prev_max = 0
        result = []

        for x, h in events:

            # Start of building
            if h < 0:
                heapq.heappush(heap, h)
                active[-h] += 1

            # End of building
            else:
                active[h] -= 1

            # Remove inactive heights
            while heap and active[-heap[0]] == 0:
                heapq.heappop(heap)

            curr_max = -heap[0]

            # Skyline changes
            if curr_max != prev_max:
                result.append([x, curr_max])
                prev_max = curr_max

        return result
