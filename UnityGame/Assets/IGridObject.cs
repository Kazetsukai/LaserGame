using System;
using UnityEngine;

public interface IGridObject
{
	GridSquare ParentSquare {get; set;}
	void Strike(LaserMove laser);
}

