using UnityEngine;
using System.Collections;

public class TileDocking : MonoBehaviour {

    private GameObject TileObject;

    public void SetTileObject(GameObject obj)
    {
        ClearTileObject();
        var pos = this.transform.position;
        pos.y += 0.1f;

        TileObject = (GameObject) Instantiate(obj, pos, Quaternion.AngleAxis(90f, Vector3.left));

    }

    public void ClearTileObject()
    {
        Debug.Log("ClearTileObject");
        if (TileObject != null)
        {
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
