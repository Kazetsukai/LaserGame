using UnityEngine;
using System.Collections;
using System;

public class SplitterBehavior : MonoBehaviour, IGridObject
{
    // Use this for initialization
    void Start()
    {
        transform.position += new Vector3(0, 0.4f, 0);
    }

    // Update is called once per frame
    void Update()
    {

    }

    public GridSquare ParentSquare { get; set; }

    public void Strike(LaserMove laser)
    {
        Destroy(laser.gameObject);

        LaserMove l1 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l1.light.enabled = true;
        l1.Direction = laser.Direction.RotateLeft();
        l1.Colour = Color.red;
        l1.Move();


        LaserMove l2 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l2.light.enabled = true;
        l2.Direction = laser.Direction;
        l2.Colour = Color.green;
        l2.Move();


        LaserMove l3 = ((GameObject)Instantiate(laser.gameObject)).GetComponent<LaserMove>();
        l3.light.enabled = true;
        l3.Direction = laser.Direction.RotateRight();
        l3.Colour = Color.blue;
        l3.Move();
    }
}


public static class GridDirectionExtensions
{
    public static GridDirection RotateLeft(this GridDirection dir)
    {
        return (GridDirection)(((int)dir + 3) % 4);
    }
    public static GridDirection RotateRight(this GridDirection dir)
    {
        return (GridDirection)(((int)dir + 1) % 4);
    }
}
