using UnityEngine;
using System.Collections;
using System.Linq;

public class TurretShooter : MonoBehaviour, IGridObject {

    float time = 0;

    public Object LaserObject;
    
	public GridSquare ParentSquare {get; set;}
	
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
        time += Time.deltaTime;

        //Debug.Log(time);

        if (time > 1)
        {
            time--;

            var laser = Instantiate(LaserObject) as GameObject;
			
			var script = laser.GetComponent<LaserMove>();
			
			var gridObject = this.GetComponents<MonoBehaviour>().FirstOrDefault(x => x is IGridObject) as IGridObject;
			
			var square = gridObject.ParentSquare;
			script.From = square.transform.position + new Vector3(0, 0.5f, 0);
			script.To = square.East.transform.position + new Vector3(0, 0.5f, 0);
			script.TerminalSquare = square.East;
			script.Direction = GridDirection.East;
        }
	}
	
	public void Strike (LaserMove laser)
	{
		
	}
}
