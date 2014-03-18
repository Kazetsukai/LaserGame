﻿using UnityEngine;
using System.Collections;

public class CreateGrid : MonoBehaviour {
	public GameObject piece;
	public GameObject Target;
	public GameObject Wall;
	
	// Use this for initialization
	void Start () {
		
		GridSquare[,] grid = new GridSquare[10,10];
		
		var gridSquareSize = ((BoxCollider)piece.collider).size.x;
		
		for (int x = 0; x < grid.GetLength(0); x++)
		{
			for (int y = 0; y < grid.GetLength(1); y++)
			{
				var gameObject = Instantiate(piece, new Vector3(x * gridSquareSize - grid.GetLength(0) * gridSquareSize / 2f, 0.1f, y * gridSquareSize - grid.GetLength(0) * gridSquareSize / 2f), Quaternion.identity) as GameObject;
				
				var gridSquare = gameObject.GetComponent<GridSquare>();
				
				grid[x,y] = gridSquare;
				
				if (Random.value > 0.8f)
					gridSquare.SetTileObject(Wall);
			}
		}
		
		grid[7,7].SetTileObject(Target);
		
		int width = grid.GetLength(0);
		int height = grid.GetLength(1);
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				if (x > 0) grid[x,y].West = grid[x-1,y];
				if (x < width-1) grid[x,y].East = grid[x+1,y];
				if (y > 0) grid[x,y].South = grid[x,y-1];
				if (y < height-1) grid[x,y].North = grid[x,y+1];
			}
		}
	}
	
	// Update is called once per frame
	void Update () {
		var deltaTime = Time.deltaTime;
		foreach(LaserMove laser in GameObject.FindObjectsOfType(typeof(LaserMove)) ){
			laser.Update(deltaTime);
		}
	}
}
