using UnityEngine;
using System.Collections;
using System.Linq;

public class GridSquare : MonoBehaviour {

    private GameObject TileObject;
	
    public GridSquare North;
    public GridSquare South;
    public GridSquare East;
    public GridSquare West;

    public int X;
    public int Y;
	
    public void SetTileObject(GameObject obj)
    {
		// If we are setting a mirror on a mirror...
		// Just Phillip Rayner it!
		if (TileObject != null)
		{
			var oldMirror = TileObject.GetComponent<Mirror>();
			var newMirror = obj.GetComponent<Mirror>();
			if(oldMirror != null && newMirror != null){
				oldMirror.Flip();
				return;
			}
		}
		
        ClearTileObject();
        var pos = this.transform.position;
        pos.y += 0.1f;

        TileObject = (GameObject) Instantiate(obj, pos, Quaternion.identity);
		
		var gridObject = TileObject.GetComponents<MonoBehaviour>().Single(x => x is IGridObject) as IGridObject;

		gridObject.ParentSquare = this;
    }

    public void ClearTileObject()
    {
        if (TileObject != null)
        {
            var gridObject = TileObject.GetComponents<MonoBehaviour>().Single(x => x is IGridObject) as IGridObject;
					
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
		if (TileObject != null)
		{
			var script = TileObject.GetScript<IGridObject>();
			if (script != null)
				script.Strike(laser);
		}

        laser.Move();
	}
}
