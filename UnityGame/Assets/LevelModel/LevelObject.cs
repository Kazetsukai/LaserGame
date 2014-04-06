using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Xml.Serialization;
using UnityEngine;

namespace Assets.LevelModel
{
    public class LevelObject
    {
        public string Name;

        [XmlAttribute]
        public int X;

        [XmlAttribute]
        public int Y;
    }
}
