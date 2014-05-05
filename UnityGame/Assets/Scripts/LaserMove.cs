using UnityEngine;
using System.Collections;

public class LaserMove : MonoBehaviour {
	
	public Vector3 From;
	public Vector3 To;
	public GridSquare TerminalSquare;
	public GridDirection Direction;
    public Color Colour;

	public float Progress = 0;
	public float Speed = 1;
	
	// Use this for initialization
	void Start ()
    {
        renderer.material.color = Colour;

		transform.position = From;
	}
	
	void Update(){}//nothing
	
	// Update is called once per frame
	public void Update(float deltaTime)
    {
        renderer.material.color = Colour;

        Progress += deltaTime * Speed;
		if (Progress > 1)
		{
			Progress = 1;
			TerminalSquare.Notify(this);
		}
		
        transform.position = (1 - Progress) * From + Progress * To;
	}

    public void Move()
    {
        GridSquare nextSquare = TerminalSquare.North;

        From = TerminalSquare.transform.position + new Vector3(0, 0.5f, 0);
        Progress %= 1.0f;

        switch (Direction)
        {
            case GridDirection.North:
                nextSquare = TerminalSquare.North;
                break;
            case GridDirection.East:
                nextSquare = TerminalSquare.East;
                break;
            case GridDirection.South:
                nextSquare = TerminalSquare.South;
                break;
            case GridDirection.West:
                nextSquare = TerminalSquare.West;
                break;
        }

        if (nextSquare == null)
            Destroy(gameObject);
        else
        {
            To = nextSquare.transform.position + new Vector3(0, 0.5f, 0);
            TerminalSquare = nextSquare;
        }
    }
}
