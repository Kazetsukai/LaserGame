using UnityEngine;
using System.Collections;

public class CreateGrid : MonoBehaviour {
	public GameObject piece;
	public GameObject Target;
	public GameObject Wall;

    public GridSquare[,] Grid;

	// Use this for initialization
	void Start () {

        Grid = new GridSquare[10, 10];
		
		var gridSquareSize = ((BoxCollider)piece.collider).size.x;

        for (int x = 0; x < Grid.GetLength(0); x++)
		{
            for (int y = 0; y < Grid.GetLength(1); y++)
			{
                var gameObject = Instantiate(piece, new Vector3(x * gridSquareSize - Grid.GetLength(0) * gridSquareSize / 2f, 0.1f, y * gridSquareSize - Grid.GetLength(0) * gridSquareSize / 2f), Quaternion.identity) as GameObject;
				
				var gridSquare = gameObject.GetComponent<GridSquare>();

                gridSquare.X = x;
                gridSquare.Y = y;

                Grid[x, y] = gridSquare;
				
				if (Random.value > 0.8f)
					gridSquare.SetTileObject(Wall);
			}
		}

        Grid[7, 7].SetTileObject(Target);

        int width = Grid.GetLength(0);
        int height = Grid.GetLength(1);
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
                if (x > 0) Grid[x, y].West = Grid[x - 1, y];
                if (x < width - 1) Grid[x, y].East = Grid[x + 1, y];
                if (y > 0) Grid[x, y].South = Grid[x, y - 1];
                if (y < height - 1) Grid[x, y].North = Grid[x, y + 1];
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
