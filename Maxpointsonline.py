class Solution:
    def maxPoints(self, points):
        n = len(points)

        if n <= 2:
            return n

        max_points = 1

        # Custom GCD function
        def gcd(a, b):
            while b:
                a, b = b, a % b
            return a

        for i in range(n):
            slopes = {}

            for j in range(i + 1, n):
                dx = points[j][0] - points[i][0]
                dy = points[j][1] - points[i][1]

                g = gcd(dx, dy)

                dx //= g
                dy //= g

                slope = (dx, dy)

                if slope in slopes:
                    slopes[slope] += 1
                else:
                    slopes[slope] = 1

                max_points = max(max_points, slopes[slope] + 1)

        return max_points
