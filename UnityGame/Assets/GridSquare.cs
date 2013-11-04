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
}
