using UnityEngine;
using System.Collections;

public class GridSquare : MonoBehaviour {

    private GameObject TileObject;
	
    public GridSquare North;
    public GridSquare South;
    public GridSquare East;
    public GridSquare West;
	
    public void SetTileObject(GameObject obj)
    {
        ClearTileObject();
        var pos = this.transform.position;
        pos.y += 0.1f;

        TileObject = (GameObject) Instantiate(obj, pos, Quaternion.AngleAxis(90f, Vector3.left));
		
		TileObject.GetComponent<GridObject>().ParentSquare = this;
		
		// Hack
		if (TileObject.name == "Mirror(Clone)")
		{
			TileObject.transform.rotation = Quaternion.AngleAxis(45f, Vector3.up);
			TileObject.transform.position += new Vector3(0, 0.4f, 0);
		}
    }

    public void ClearTileObject()
    {
        Debug.Log("ClearTileObject");
        if (TileObject != null)
        {
			var gridObject = TileObject.GetComponent<GridObject>();
			if (gridObject != null) gridObject.ParentSquare = null;
            Destroy(TileObject);
        }
		
        
		TileObject = null;
    }

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	public void Notify(LaserMove laser)
	{
		GridSquare nextSquare = North;
		
		if (TileObject != null)
		{
			var script = TileObject.GetScript<IGridObject>();
			if (script != null)
				script.Strike(laser);
		}
		
		laser.From = this.transform.position + new Vector3(0, 0.5f, 0);
		laser.Progress %= 1.0f;
		
		switch (laser.Direction)
		{
		case GridDirection.North:
			nextSquare = North;
			break;
		case GridDirection.East:
			nextSquare = East;
			break;
		case GridDirection.South:
			nextSquare = South;
			break;
		case GridDirection.West:
			nextSquare = West;
			break;
		}
		
		if (nextSquare == null) 
			Destroy(laser.gameObject);
		else
		{
			laser.To = nextSquare.transform.position + new Vector3(0, 0.5f, 0);
			laser.TerminalSquare = nextSquare;
		}
	}
}
