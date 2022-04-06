# What is this repository about?
This repository is my implementation (perhaps not very accurate but anyways) of delaunay triangulation algorithm for 2d.
<br>
<br>
### Note
I'm not a professional java programmer and I'm not exactly a specialist in this stuff so there could be mistakes and inaccuracies basically anywhere, so be careful while using this repository.

# Task
*Text below was translated using google translate*
The goal is to implement an algorithm for triangulating a 2 dimensional domain using the Delaunay method.
<br>
#### Initial data
An area in the form of a curvilinear ring bounded by two closed flat polygons, each of which is defined by a sequence of segments.

# Algorithm
1. Generate array of points representing object described in [**Initial data**](#initial-data).
2. Using that array we will generate every possible set of  points taken in threes.
3. For every set of points we need to check that there are no points inside the circle built on three points. If there are no points inside circle, then we can generate tree edges. Combine this three edges into triangle.
4. Leave only "good" edges.
	* Take two generated triangles that have common edge. For example, we took triangles ABD and BDC with common edge DB. Now basically we have quadrilateral ABCD with diagonal DB.
	* Calculate every angle inside quadrilateral ABCD with diagonal DB (6 angles) and find the minimum angle.
	* Now lets change diagonal inside ABCD (was DB and now diagonal is AC). 
	* Find minimum angle inside quadrilateral ABCD with diagonal AC.
	* If minimum angle of quadrilateral with changed diagonal if greater than minimum angle of initial quadrilateral, we wouldn't use edge DB.
5. Now we are left with multiple edges that are "good". Each edge consists of two connected points. Well, now we need to plot all this stuff by connecting every point in right manner (to have at the end only "good" edges). So we need to create a path from first point to last one.
6. That's it, now we can easily plot the resulting triangulation.
