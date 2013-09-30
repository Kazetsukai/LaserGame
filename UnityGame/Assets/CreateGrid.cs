using UnityEngine;
using System.Collections;

public class CreateGrid : MonoBehaviour {
	
	public Transform piece;
	
	// Use this for initialization
	void Start () {
		for (int x = 0; x < 6; x++)
		{
			for (int y = 0; y < 6; y++)
			{
				Instantiate(piece, new Vector3(x * 1.5f - 3.8f, 0.1f, y * 1.5f - 3.8f), Quaternion.identity);
			}
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
